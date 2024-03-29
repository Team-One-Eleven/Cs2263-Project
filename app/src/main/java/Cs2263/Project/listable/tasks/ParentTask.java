/**
 * Parent Task Class
 *
 *  The main task class, it holds a list od childTasks.
 *  It also holds a list of String id's that correspond to
 *  the sections which hold it.
 *
 * @author  Traae
 * @version 1.0
 * @since 3/31/2021
 */

package Cs2263.Project.listable.tasks;

import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.listable.ListableType;
import Cs2263.Project.listable.lists.ListArchetype;
import Cs2263.Project.listable.lists.Section;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class ParentTask extends TaskArchetype implements ListableItem, Serializable {
    // Variables
    // Listable type
    private static final ListableType type = ListableType.ParentTask;
    // Instance variables
    private ArrayList<Double> parentSections;
    private ArrayList<ChildTask> childTasks;



    // Constructor
    // NO ARGUMENTS for Serializable
    public ParentTask(){
        parentSections = new ArrayList<>();
        childTasks = new ArrayList<>();
        super.setUsingDuedate(true);
    }
    // Methods
    // GETTERS
    public ArrayList<Double> getParentSections() {
        return parentSections;
    }
    public ArrayList<ChildTask> getChildTasks() {
        return childTasks;
    }
    // Listable Implementation
    @Override
    public ListableType getType() {
        return type;
    }
}
