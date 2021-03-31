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

import Cs2263.Project.listable.UserCredentials;
import Cs2263.Project.listable.lists.Section;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.listable.tasks.ChildTask;
import Cs2263.Project.listable.tasks.ParentTask;
import Cs2263.Project.listable.tasks.TaskStatus;
import Cs2263.Project.tools.ItemFactory;
import Cs2263.Project.tools.FileManager;
import Cs2263.Project.tools.SearchEngine;
import Cs2263.Project.user.User;
import Cs2263.Project.tools.UserFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.LinkedList;

public class Orchestrator {
    // Singleton instance
    private static Orchestrator instance = null;


    // Variables
    // User - related
    private String userDataFilePath;
    private LinkedList<UserCredentials> userList;
    private User activeUser;
    private UserCredentials activeInfo;
    private LinkedList<ToDoList> masterList;
    // Tools
    private FileManager fileManager;
    private SearchEngine searchEngine;
    // Factories
    private UserFactory userFactory;
    private ItemFactory itemFactory;
    // System Settings
    private Configuration config;

    // Singleton Constructor & getInstance;
    private Orchestrator() {
        // Load config
        // Load userList

        // Setup tools
        fileManager = new FileManager(this);
        searchEngine = new SearchEngine(this);
        userFactory = new UserFactory(this);
        itemFactory = new ItemFactory(this);

    }
    public static Orchestrator getInstance() {
        if (instance == null){
            instance = new Orchestrator();
        }
        return instance;
    }

    // Methods
    // GETTERS
    // Get Tools
    public FileManager getFileManager() {
        return fileManager;
    }
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
    public UserFactory getUserFactory() {
        return userFactory;
    }
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
    public boolean registerUser(String email, String password){
        for (UserCredentials u: userList){
            if (u.getUserEmail() == email){
                return false;
            }
            else {
                String newUserID = getNextUserID();
                UserCredentials newInfo =  userFactory.makeUserInfo(email, password, newUserID);
                User newUser = userFactory.makeUser();
                userList.add(newInfo);
                // TODO filmangaer save user list
                activeUser = newUser;
                activeInfo = newInfo;
                return true;
            }
        }
        return false;
    }
    public boolean loginUser(String email, String password) throws IOException {
        for (UserCredentials info : userList){
            if ((info.getUserEmail() == email) & (info.getUserPassword() == password)){
                activeInfo = info;
                activeUser = fileManager.loadUser();
                return true;
            }
            else {
                return false;
            }
        }
        return  false;
    }
    private void checkDueDates(){
        Date now = new Date();
        for (ParentTask p : activeUser.getTheTasks()){
            if ((p.getStatus() == TaskStatus.incomplete) & (p.isUsingDuedate())) {
                for (ChildTask c : p.getChildTasks()){
                    if ((c.getStatus() == TaskStatus.incomplete) & (c.isUsingDuedate())){
                        if (c.getDueDate().before(now)){
                            c.setStatus(TaskStatus.overdue);
                        }
                    }
                }
                if (p.getDueDate().before(now)){
                    p.setStatus(TaskStatus.overdue);
                }
            }
        }
    }
    private void createMasterList(){
        if (masterList == null){
            masterList = new LinkedList<ToDoList>();
        }
        masterList.addAll(activeUser.getTheLists());

        for (ParentTask task : activeUser.getTheTasks()){
            for (String id: task.getParentSections()){
                for (ToDoList list : masterList){
                    for (Section section : list.getSections()){
                        if (section.getId() == id){
                            section.getTasks().add(task);
                        }
                    }
                }
            }
        }
    }
    private void startup() throws IOException {
        userList = fileManager.loadUserList();
    }
    public void exit(){
        // NEEDS DEVELOPED
    }
}
