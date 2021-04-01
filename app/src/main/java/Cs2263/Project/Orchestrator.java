/**
 * Orchestrator for the system.
 *
 * Handles all of the logic and/or the classes that handles the logic
 * for the system and makes it accessible to the UI.
 *
 * STILL DEVELOPING
 *
 * @author  Traae
 * @version 0.5
 * @since 3/25/2021
 */

package Cs2263.Project;

import Cs2263.Project.tools.ListManager;
import Cs2263.Project.user.UserCredentials;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.tools.*;
import Cs2263.Project.user.User;

import javax.security.auth.login.FailedLoginException;
import java.io.IOException;
import java.util.LinkedList;

public class Orchestrator {
    // Default Admin Credentials Globals
    private static final String ADMIN_EMAIL_DEFAULT = "Admin";
    private static final String ADMIN_PASSWORD_DEFAULT = "123password";
    private static final String ADMIN_ID_DEFAULT = "ADMIN";

    // Singleton instance
    private static Orchestrator instance = null;

    // Variables
    // User - related
    private LinkedList<UserCredentials> userList;
    private User activeUser;
    private UserCredentials activeInfo;
    private LinkedList<ToDoList> masterList;
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
    private Orchestrator() throws IOException {
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
    public static Orchestrator getInstance() throws IOException {
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
    // Get Settings Variables
    public String getNextUserID() {
        /**
         * Returns a unique string for identifiengs users in the system.
         * Each call of this will increment the user Id seed and create a
         * string with it. It doesn't matter if this function gets called
         * arbitrarily, or if the user that's being made fails. The seed only ever
         * goes up.
         *
         * Theoretically it could max, however it is a double, this is unfeasible.
         * Acceptable for development, create a more complicated ide system later.
         *
         */
        return "User" + config.getNextUserIDseed();
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
    public LinkedList<ToDoList> getMasterList() {
        return masterList;
    }
    public LinkedList<UserCredentials> getUserList() {
        return userList;
    }
    // Get Config
    public Configuration getConfig() {
        return config;
    }

    // System Methods
    public boolean registerUser(String email, String password) throws IOException {
        for (UserCredentials u: userList){
            if (u.getUserEmail() == email){
                return false;
            }
            else {
                String newUserID = getNextUserID();
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
        listManager.deconstructMasterList();
        fileManager.saveUser(activeUser, activeInfo);
        fileManager.saveUserList();
        activeInfo = null;
        activeUser = null;
        masterList = null;
    }
    public void autosave() throws IOException {
        listManager.deconstructMasterList();
        fileManager.saveUser(activeUser, activeInfo);
        fileManager.saveUserList();
        listManager.constructMasterList();
    }

    public void exit() throws IOException {
        logoutUser();
        fileManager.saveConfiguration();
    }

    public void makeDefaultAdmin() throws IOException {
        UserCredentials info = userFactory.makeUserInfo(ADMIN_EMAIL_DEFAULT, ADMIN_PASSWORD_DEFAULT, ADMIN_ID_DEFAULT);
        User admin = userFactory.makeUser();
        admin.setFirstName("System");
        admin.setLastName("Administrator");
        admin.setBiography("");
        userList.add(info);
        fileManager.saveUserList();
        fileManager.saveUser(admin, info);
    }

    private void makeExampleUsers() throws IOException {
        for (int i=3; i>0; i--){
            UserCredentials info = userFactory.makeUserInfo("example" + i + "@example.com", "password", "example" + i);
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
