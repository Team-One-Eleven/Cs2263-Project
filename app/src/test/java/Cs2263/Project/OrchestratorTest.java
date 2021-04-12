/**
 * Orchestrator for the system.
 *
 * Supposed to be in the vein of a facade, and NOT a god object.
 * Handles all of the logic and/or the classes that handles the logic
 * for the system and makes it accessible to the UI.
 *
 * Currently it handles the compound methods for user's: list and data, registering, login, and log out.
 * The exit and autosave methods handle the safe preservation of data, and should be called by the
 * app during those titular operations.
 *
 * The Orchestrator is a singleton, which then holds a single instance of various tools that are needed, such
 * as the File Manager, Search Engine, System Configuration, and Factories.
 *
 *
 * @author  Traae
 * @version 1.0
 * @since 4/6/2021
 */

package Cs2263.Project;

import Cs2263.Project.listable.UserCredentials;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.tools.*;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.FailedLoginException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrchestratorTest {

    @Test void TESTgetInstance() throws IOException {
        Orchestrator o = Orchestrator.getInstance();
        assertTrue(o != null);
    }

    @Test void TESTregisterUser() throws IOException   {
        Orchestrator o = Orchestrator.getInstance();

        String emailTEST = "test@gmail.com";
        String passwordTEST = "testPassword";

        assertTrue(o.registerUser(emailTEST, passwordTEST));

        boolean found = false;
        for (UserCredentials u : o.getUserList()){
            if ((u.getUserEmail() == emailTEST) & (u.getUserPassword() == passwordTEST)){
                found = true;
            }
        }
        assertTrue(found);
    }


    @Test void loginUserTEST() throws IOException, FailedLoginException {
        Orchestrator o = Orchestrator.getInstance();

        if (!o.getUserList().isEmpty()){
            UserCredentials u = o.getUserList().get(0);
            assertTrue(o.loginUser(u.getUserEmail(), u.getUserPassword()));
        }

    }
    public void logoutUser() throws IOException {
        /**
         * This is a compound method to logout the current user.
         *
         * first, deconstructs the master list structure.
         * second, saves all the users data.
         * third, sets all the active user variables to null.
         *
         */
        listManager.deconstructMasterList();
        fileManager.saveUser(activeUser, activeInfo);
        fileManager.saveUserList();
        activeInfo = null;
        activeUser = null;
        masterList = null;
    }
    public void autosave() throws IOException {
        /**
         * This is a compound method the provide the methods to perform a safe save of data.
         * it have been named autosave to match its functional usage in the app/driver.
         *
         * It DOESN'T do anything without being called.
         *
         * This method can also be called to invoke a manual save. The process is the same.
         */
        // Deconstruct the master list to avoid recursive serialisation, save the data, build the mast list again.
        listManager.deconstructMasterList();
        fileManager.saveUser(activeUser, activeInfo);
        fileManager.saveUserList();
        listManager.constructMasterList();
    }

    public void exit() throws IOException {
        /**
         * This is a compound method to be called when exiting the app.
         * This will  log out the current user, and save the config data.
         */
        if (activeUser != null){
            logoutUser();
        }
        fileManager.saveConfiguration();
    }


    public void makeDefaultUserList() throws IOException {
        /**
         * This is a compound method for initializing the Admin abd Userlist for the program.
         * it will be called anytime that data for the program isn't found.
         *
         * Thus, it should be called the first time a new instance of the app is installed, and
         * anytime the data has been deleted or interfered with.
         */
        UserCredentials info = userFactory.makeUserInfo(ConfigurationTest.ADMIN_EMAIL_DEFAULT,
                ConfigurationTest.ADMIN_PASSWORD_DEFAULT,
                ConfigurationTest.ADMIN_ID_DEFAULT);
        User admin = userFactory.makeUser();
        admin.setFirstName("System");
        admin.setLastName("Administrator");
        admin.setBiography("");

        if (userList == null){
            userList = new ArrayList<UserCredentials>();
        }
        userList.add(info);

        fileManager.saveUserList();
        fileManager.saveUser(admin, info);
        makeExampleUsers();
    }

    private void makeExampleUsers() throws IOException {
        /**
         * This is a compound function the should only be called by makeDefaultUserList()
         *
         * for Testing purposes. Delete or comment away later.
         *
         * This function will further populated the the user list with some data for the new admin to check.
         *
         */
        for (int i=3; i>0; i--){
            UserCredentials info = userFactory.makeUserInfo("example" + i + "@example.com", "password",  i);
            User example = userFactory.makeUser();
            example.setFirstName("example");
            example.setLastName("Number: " + i);
            example.setBiography("");
            userList.add(info);
            fileManager.saveUserList();
            fileManager.saveUser(example, info);
        }
    }

}
