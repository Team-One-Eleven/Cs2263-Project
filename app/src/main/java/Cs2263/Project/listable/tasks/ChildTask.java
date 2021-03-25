/**
 * Child Task Class
 *
 * STILL DEVELOPING
 * Needs to have its constructor relove its constructor and factory methods
 *
 * @author  Traae
 * @version 0.0
 * @since 3/25/2021
 */

package Cs2263.Project.listable.tasks;

import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.listable.ListableType;

public class ChildTask extends TaskArchetype implements ListableItem {
    // Variables
    // Listable type
    private static final ListableType type = ListableType.ChildTask;
    // Instance
    private ParentTask parent;


    @Override
    public ListableType getType() {
        return type;
    }
}
