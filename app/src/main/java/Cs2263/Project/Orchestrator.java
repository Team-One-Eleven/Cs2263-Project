
package Cs2263.Project;

import Cs2263.Project.listable.lists.Section;
import Cs2263.Project.listable.tasks.ChildTask;
import Cs2263.Project.listable.tasks.ParentTask;
import Cs2263.Project.listable.tasks.TaskPriority;
import Cs2263.Project.tools.ListManager;
import Cs2263.Project.listable.UserCredentials;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.tools.*;

import javax.security.auth.login.FailedLoginException;
import java.io.IOException;
import java.util.ArrayList;

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
    private Orchestrator(){
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
    public static Orchestrator getInstance(){
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
    public boolean registerUser(String email, String password)   {
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
    public boolean loginUser(String email, String password) throws FailedLoginException {
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
    public void logoutUser() {
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
    public void autosave() {
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

    public void exit() {
        /**
         * This is a compound method to be called when exiting the app.
         * This will  log out the current user, and save the config data.
         */
        if (activeUser != null){
            logoutUser();
        }
        fileManager.saveConfiguration();
    }




    public ArrayList<UserCredentials> makeDefaultUserList() {
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
        admin.setBiography("This biography was written by makeDefaultAdmin()");

        if (userList == null){
            userList = new ArrayList<UserCredentials>();
        }
        userList.add(info);
        makeExampleUsers();

        return userList;
    }

    private void makeExampleUsers() {
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
            example.setBiography("This biography was written my make Example Users \n Example #" + i);

            example.getTheLists().add(makeAnExampleListStructure(i));

            userList.add(info);

        }
    }

    public User makeDefaultUser(){
        UserCredentials info = userFactory.makeUserInfo(
                "user@eaxmple.com",
                "password123",
                config.getNextUserIDseed());
        User user = userFactory.makeUser();
        user.setFirstName("User");
        user.setLastName("Example");
        user.setBiography("This biography was written by makeDefaultUser()");

        user.getTheLists().add(makeAnExampleListStructure(1));
        user.getTheLists().add(makeAnExampleListStructure(500));

        userList.add(info);
        return user;
    }

    private ToDoList makeAnExampleListStructure(int i){
        ToDoList theList = itemFactory.makeToDOList();

        theList.setTitle("Top level list" + i);
        theList.setDescription("This is an example list "+ i);

            ParentTask defTask = itemFactory.makeParentTask();
            defTask.setTitle("Default Task " + i);
            defTask.setDescription("A task that should appear in the default section " + i);
            defTask.setPriority(TaskPriority.High);

                ChildTask defChild1 = itemFactory.makeChildTask();
                defChild1.setTitle("This is a child task" + i);
                defChild1.setDescription("should be in the defatault task of the default section"+ i);

                ChildTask defChild2 = itemFactory.makeChildTask();
                defChild2.setTitle("This is another child task"+ i);
                defChild2.setPriority(TaskPriority.Highest);

                defTask.getChildTasks().add(defChild1);
                defTask.getChildTasks().add(defChild2);

            theList.getDefaultSection().addTask(defTask);

        Section a = itemFactory.makeSection();
        a.setTitle("Example section A"+ i);
        a.setDescription("example section description"+ i);

            ParentTask taskA = getItemFactory().makeParentTask();
            taskA.setTitle("Fill out my to do list"+ i);
            taskA.setDescription("I really should get around to making a to do list one of these days"+ i);
            a.addTask(taskA);

        ToDoList sublistA1 = itemFactory.makeToDOList();
        sublistA1.setTitle("Grocery shopping"+ i);
        sublistA1.setDescription("Im hung, I'm like mungo hungo."+ i);

        ParentTask milk = getItemFactory().makeParentTask();
        milk.setTitle("buy milk"+ i);
        milk.setDescription("tfw no big tiddy mommy milker goth gf."+ i);
        sublistA1.getDefaultSection().addTask(milk);

        ParentTask eggs = getItemFactory().makeParentTask();
        eggs.setTitle("buy eggs"+ i);
        eggs.setDescription("Robotnik");
        sublistA1.getDefaultSection().addTask(eggs);
        a.getLists().add(sublistA1);


        ToDoList subListA2 = itemFactory.makeToDOList();
        subListA2.setTitle("MORE Grocery shopping"+ i);
        subListA2.setDescription("I eat the food, I shit the food, it never ends");

        ParentTask milk2 = getItemFactory().makeParentTask();
        milk2.setTitle("buy MOAR milk"+ i);
        milk2.setDescription("make it stop");
        subListA2.getDefaultSection().addTask(milk2);

        ParentTask eggs2 = getItemFactory().makeParentTask();
        eggs2.setTitle("so many eggs"+ i);
        eggs2.setDescription("they smell.");
        subListA2.getDefaultSection().addTask(eggs2);
        a.getLists().add(subListA2);


        Section b = itemFactory.makeSection();
        b.setTitle("Example section b");
        b.setDescription("All the milk"+ i);
        b.addTask(milk);
        b.addTask(milk2);
        theList.getSections().add(b);

        return theList;
    }

}
