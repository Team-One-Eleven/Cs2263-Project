/**
 * The List Abstract class
 *
 * provides the common structure among the 2 types of lists
 *
 * STILL DEVELOPING
 * Needs constructors and factory methods reolved across its inheritence family
 *
 * Responsibilities need to be resolved across Section and todoList.
 *
 * @author  Traae
 * @version 0.0
 * @since 3/25/2021
 */


package Cs2263.Project.listable.lists;

import java.util.LinkedList;

public abstract class ListArchetype {
    // Variables
    private String title;
    private String description;
    private String id;
    private LinkedList<ToDoList> sublists;


    // Methods
    // GETTERS
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getId() {
        return id;
    }
    public LinkedList<ToDoList> getSublists() {
        return sublists;
    }

    // SETTERS
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
