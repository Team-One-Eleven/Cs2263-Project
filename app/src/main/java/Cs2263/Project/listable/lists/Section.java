/**
 * Section Class
 *
 * Sub divides lists and holds contents
 *
 * creation is handled at ItemFactory
 *
 * @author  Traae
 * @version 1.0
 * @since 3/31/2021
 */

package Cs2263.Project.listable.lists;

import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.listable.ListableType;
import Cs2263.Project.listable.lists.ListArchetype;
import Cs2263.Project.listable.tasks.ParentTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Section extends ListArchetype implements ListableItem, Serializable {
    // Variables
    // Listable type
    private static final ListableType type = ListableType.Section;
    // Instance variables
    private ArrayList<ParentTask> tasks;
    private ArrayList<ToDoList> lists;
    private Double id;

    // Constructor
    // NO ARGUMENTS for Serializable
    public Section(){
        tasks = new ArrayList<>();
        lists = new ArrayList<>();
    }

    //Methods
    // GETTERS
    public ArrayList<ParentTask> getTasks() {
        return tasks;
    }
    public double getId() {
        return id;
    }
    public ArrayList<ToDoList> getLists() {
        return lists;
    }

    // ID initialization
    public void initId(double id) {
            this.id = id;
    }


    // Listable Implementation
    @Override
    public ListableType getType() {
        return type;
    }
}
