/**
 * User class
 *
 * The regular user class. In addition to the UserArchetype's properties
 * it has a list of tasks & lists and other properties supporting that.
 *
 * @author  Traae
 * @version 1.0
 * @since 4/6/2021
 */

package Cs2263.Project;

import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.listable.tasks.ParentTask;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {


    // Variables
    private ArrayList<ParentTask> theTasks;
    private ArrayList<ToDoList> theLists;
    private ToDoList mainList;
    private double sectionIDseed;
    private String firstName;
    private String lastName;
    private String biography;
    private double userId;


    // Constructor
    public User(){
        firstName = "Your";
        lastName = "Name";
        biography = "Your bio.";
        theTasks = new ArrayList<>();
        theLists = new ArrayList<>();
    }

    // Methods
    //GETTERS
    public ArrayList<ParentTask> getTheTasks() {
        return theTasks;
    }
    public ArrayList<ToDoList> getTheLists() {
        return theLists;
    }
    public double getNextSectionID() {
        /**
         * returns a String to be used as an ID for a toDoList.
         * is based off an incrementing double.
         */
        sectionIDseed++;
        return sectionIDseed;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getBiography() {
        return biography;
    }
    // SETTERS
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setMainList(ToDoList mainList) {
        this.mainList = mainList;
        if (!theLists.contains(this.mainList)){
            theLists.add(mainList);
        }
    }
}
