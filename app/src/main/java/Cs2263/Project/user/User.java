/**
 * User class
 *
 * The regular user class. In addition to the UserArchetype's properties
 * it has a list of tasks & lists and other properties supporting that.
 *
 * @author  Traae
 * @version 1.0
 * @since 3/25/2021
 */

package Cs2263.Project.user;

import Cs2263.Project.listable.lists.ListArchetype;
import Cs2263.Project.listable.tasks.TaskArchetype;

import java.io.Serializable;
import java.util.LinkedList;

public class User extends UserArchetype implements Serializable {

    // Variables
    private LinkedList<TaskArchetype> theTasks;
    private LinkedList<ListArchetype> theLists;
    private LinkedList<String> listIDs;
    private double listIDseed;


    // Constructor
    public User(String id, String first, String last, String email, String password) {
        super(id, first, last, email, password);
    }

    // Methods
    //GETTERS
    public LinkedList<TaskArchetype> getTheTasks() {
        return theTasks;
    }
    public LinkedList<ListArchetype> getTheLists() {
        return theLists;
    }
    public String getNextListID() {
        /**
         * returns a String to be used as an ID for a toDoList.
         * is based off an incrementing double.
         */
        listIDseed++;
        return "list" + listIDseed;
    }
    // OTHER
    public void newPictureFile(String picFilePath){
        // NEEDS DEVELOPED
    }
}
