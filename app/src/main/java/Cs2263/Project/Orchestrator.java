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

import Cs2263.Project.listable.ItemFactory;
import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.tools.DateHandler;
import Cs2263.Project.tools.FileManager;
import Cs2263.Project.tools.SearchEngine;
import Cs2263.Project.user.UserArchetype;
import Cs2263.Project.user.UserFactory;

import java.util.LinkedList;

public class Orchestrator {
    // Variables
    // User - related
    private String userDataFilePath;
    private UserArchetype activeUser;
    private LinkedList<ListableItem> masterList;
    // Tools
    private FileManager fileManager;
    private SearchEngine searchEngine;
    private DateHandler dateHandler;
    // Factories
    private UserFactory userFactory;
    private ItemFactory itemFactory;
    // System Settings
    private double userIDseed;
    private String baseDirectory;
    private boolean logEnabled;
    private String logFilePath;
    private int defaultItemNum;
    private int currentItemNum;

    // Methods

    // GETTERS
    // Get Tools
    public FileManager getFileManager() {
        return fileManager;
    }
    public SearchEngine getSearchEngine() {
        return searchEngine;
    }
    public DateHandler getDateHandler() {
        return dateHandler;
    }
    // Get Settings Variables
    public int getCurrentItemNum() {
        return currentItemNum;
    }
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
        userIDseed++;
        return "User" + userIDseed;
    }
    // Get Factories
    public UserFactory getUserFactory() {
        return userFactory;
    }
    public ItemFactory getItemFactory() {
        return itemFactory;
    }
    // Get USER
    public UserArchetype getActiveUser() {
        return activeUser;
    }
    public LinkedList<ListableItem> getMasterList() {
        return masterList;
    }
    // System Methods
    public boolean registerUser(){
        //NEEDS DEVELOPED
        return true;
    }
    public boolean loginUser(){
        //NEEDS DEVELOPED
        return true;
    }
    private void checkDueDates(){
        //NEEDS DEVELOPED
    }
    public void exit(){
        // NEEDS DEVELOPED
    }
}
