/**
 * User class
 *
 * The regular user class. In addition to the UserArchetype's properties
 * it has a list of tasks & lists and other properties supporting that.
 *
 * @author  Traae
 * @version .0
 * @since 3/25/2021
 */

package Cs2263.Project.user;

import Cs2263.Project.listable.lists.ListArchetype;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.listable.tasks.ParentTask;
import Cs2263.Project.listable.tasks.TaskArchetype;

import java.io.Serializable;
import java.util.LinkedList;

public class User extends UserArchetype implements Serializable {

    // Variables
    private LinkedList<ParentTask> theTasks;
    private LinkedList<ToDoList> theLists;
    private double sectionIDseed;


    // Constructor
    public User() {
        theTasks = new LinkedList<>();
        theLists = new LinkedList<>();
    }

    // Methods
    //GETTERS
    public LinkedList<ParentTask> getTheTasks() {
        return theTasks;
    }
    public LinkedList<ToDoList> getTheLists() {
        return theLists;
    }
    public String getNextSectionID() {
        /**
         * returns a String to be used as an ID for a toDoList.
         * is based off an incrementing double.
         */
        sectionIDseed++;
        return "list" + sectionIDseed;
    }
    // OTHER
    public void newPictureFile(String picFilePath){
        // NEEDS DEVELOPED
    }
}
