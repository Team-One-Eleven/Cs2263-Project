/**
 * Child Task Class
 *
 * makes up the tasks within tasks.
 * Starts with dudDate turned off, unlike ParentTasks.
 *
 * @author  Traae
 * @version 1.0
 * @since 3/31/2021
 */

package Cs2263.Project.listable.tasks;

import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.listable.ListableType;

import java.io.Serializable;

public class ChildTask extends TaskArchetype implements ListableItem, Serializable {
    // Variables
    // Listable type
    private static final ListableType type = ListableType.ChildTask;

    // Constructor
    // NO ARGUMENTS for Serializable
    public ChildTask(){
        super.setUsingDuedate(false);
    }

    //Methods
    // Listable Implementation
    @Override
    public ListableType getType() {
        return type;
    }
}
