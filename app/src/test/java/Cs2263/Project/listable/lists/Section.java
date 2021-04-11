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
import Cs2263.Project.listable.tasks.ParentTask;

import java.io.Serializable;
import java.util.LinkedList;

public class Section extends ListArchetype implements ListableItem, Serializable {
    // Variables
    // Listable type
    private static final ListableType type = ListableType.Section;
    // Instance variables
    private LinkedList<ParentTask> tasks;
    private LinkedList<ToDoList> lists;
    private String id;

    // Constructor
    // NO ARGUMENTS for Serializable
    public Section(){
        tasks = new LinkedList<>();
        lists = new LinkedList<>();
    }

    //Methods
    // GETTERS
    public LinkedList<ParentTask> getTasks() {
        return tasks;
    }
    public String getId() {
        return id;
    }
    public LinkedList<ToDoList> getLists() {
        return lists;
    }

    // ID initialization
    public void initId(String id) {
        if (id == null){
            this.id = id;
        }
    }


    // Listable Implementation
    @Override
    public ListableType getType() {
        return null;
    }
}
