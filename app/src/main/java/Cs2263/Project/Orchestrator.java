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

import Cs2263.Project.tools.ListManager;
import Cs2263.Project.listable.UserCredentials;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.tools.*;

import javax.security.auth.login.FailedLoginException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Orchestrator {


    // Singleton instance
    private static Orchestrator instance = null;

    // Variables
    // User - related
    private ArrayList<UserCredentials> userList;
    private User activeUser;
    private UserCredentials activeInfo;
    private ArrayList<ToDoList> masterList;
    private ListManager listManager;
    // Tools
    private FileManager fileManager;
    private SearchEngine searchEngine;
    // Factories
    private UserFactory userFactory;
    private ItemFactory itemFactory;
    // System Settings
    private Configuration config;

    // Singleton Constructor & getInstance;
    private Orchestrator() throws IOException{
        // Setup tools
        fileManager = new FileManager(this);
        searchEngine = new SearchEngine(this);
        userFactory = new UserFactory(this);
        itemFactory = new ItemFactory(this);
        listManager = new ListManager(this);

        // Load up the user list and the configuration.

        userList = fileManager.loadUserList();
        config = fileManager.loadConfig();

    }
    public static Orchestrator getInstance() throws IOException{
        if (instance == null){
            instance = new Orchestrator();
        }
        return instance;
    }

    // Methods
    // GETTERS
    // Get Tools
    public SearchEngine getSearchEngine() {
        return searchEngine;
    }
    // Get Factories
    public ItemFactory getItemFactory() {
        return itemFactory;
    }
    // Get USER & related
    public User getActiveUser() {
        return activeUser;
    }
    public UserCredentials getActiveInfo() {
        return activeInfo;
    }
    public ArrayList<ToDoList> getMasterList() {
        return masterList;
    }
    public ArrayList<UserCredentials> getUserList() {
        return userList;
    }
    // Get Config
    public Configuration getConfig() {
        return config;
    }

    // System Methods
    public boolean registerUser(String email, String password) throws IOException   {
        /**
         *  This is a compound method for registering a new user.
         *  It creates and entry in the user list, and a new user file, but doesn't fill them out completely.
         *  Doesn't log user in.
         *
         *  currently throws a false if the registration fails, and true if successful
         */
        for (UserCredentials u: userList){
            // first, see if the email is already in use
            if (u.getUserEmail() == email){
                return false;
            }
            else {
                // if not, grab a new user ID, fetch an info and user from the factory, fill out the basics
                // and save there info into the files.
                double newUserID = config.getNextUserIDseed();
                UserCredentials newInfo =  userFactory.makeUserInfo(email, password, newUserID);
                User newUser = userFactory.makeUser();
                userList.add(newInfo);
                fileManager.saveUserList();
                activeUser = newUser;
                fileManager.saveUser(activeUser, activeInfo);
                activeUser = null;
                return true;
            }
        }
        return false;
    }
    public boolean loginUser(String email, String password) throws IOException, FailedLoginException {
        /**
         * This is a compound method for logging in a user.
         * scans the user list, finds the matching credentials, loads their file, sets them to the active
         *
         * Then scans their list to check the due dates, and build the master list structure.
         */
        for (UserCredentials info : userList){
            if ((info.getUserEmail() == email) & (info.getUserPassword() == password)){
                activeInfo = info;
                activeUser = fileManager.loadUser(activeInfo.getUserFile());
                listManager.checkDueDates();
                listManager.constructMasterList();
                return true;
            }
            else {
                throw new FailedLoginException("Invalid login credentials");
            }
        }
        return  false;
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
        UserCredentials info = userFactory.makeUserInfo(Configuration.ADMIN_EMAIL_DEFAULT,
                Configuration.ADMIN_PASSWORD_DEFAULT,
                Configuration.ADMIN_ID_DEFAULT);
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
