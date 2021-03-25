/**
 * Parent Task Class
 *
 * STILL DEVELOPING
 * Need to resolve the constructor and factory methods
 *
 * @author  Traae
 * @version 0.0
 * @since 3/25/2021
 */

package Cs2263.Project.listable.tasks;

import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.listable.ListableType;
import Cs2263.Project.listable.lists.ListArchetype;

import java.util.LinkedList;

public class ParentTask extends TaskArchetype implements ListableItem {
    // Variables
    // Listable type
    private static final ListableType type = ListableType.ParentTask;
    // Instance variables
    private LinkedList<ListArchetype> parentLists;
    private LinkedList<ChildTask> childTasks;

    public LinkedList<ListArchetype> getParentLists() {
        return parentLists;
    }
    public LinkedList<ChildTask> getChildTasks() {
        return childTasks;
    }
    @Override
    public ListableType getType() {
        return type;
    }
}
