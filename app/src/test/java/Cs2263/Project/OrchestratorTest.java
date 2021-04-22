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

    @Test void TESTgetInstance() {
        Orchestrator o = Orchestrator.getInstance();
        assertTrue(o != null);
    }

    @Test void TESTregisterUser() {
        /**
         * Register user calls other important method that need testing.
         *
         * This test only checks to see if the the new info made it into the list.
         *
         * The actual existence of the saved dat will be tested in the file mangaer
         */

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


    @Test void loginUserTEST()throws FailedLoginException {
        /**
         * This test for a confirmed login.
         * however, in our simplification of the system,
         * I removed any shared data between the user Credentials and the user file.
         *
         * This means that I currently have no way of knowing it the correct
         * user was loaded.
         *
         * TODO fix the aforementioned problem
         */
        Orchestrator o = Orchestrator.getInstance();

        if (!o.getUserList().isEmpty()){
            UserCredentials u = o.getUserList().get(0);
            o.loginUser(u.getUserEmail(), u.getUserPassword());
            assertTrue(o.getActiveInfo() == u);
            assertTrue(o.getActiveUser() != null);
        }
    }


    @Test public void logoutUserTEST() {
        /**
         * This is another composite method containing other
         * important method in need of testing.
         *
         * This will only test the end result of the active
         * user, info, and list have been cleard.
         */

        Orchestrator o = Orchestrator.getInstance();

        o.logoutUser();
        assertTrue(o.getActiveUser() == null);
        assertTrue(o.getActiveInfo() == null);
        assertTrue(o.getMasterList() == null);

    }


}
