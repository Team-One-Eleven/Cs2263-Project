/**
 * Section Class
 *
 * Sub divides lists and holds contents
 *
 * STILL DEVELOPING
 * Needs constructors and factory methods reolved across its inheritence family
 *
 * Responsibilities need to be resolved across Section and todoList.
 *
 * @author  Traae
 * @version 1.0
 * @since 3/25/2021
 */

package Cs2263.Project.listable.lists;

import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.listable.ListableType;
import Cs2263.Project.listable.lists.ListArchetype;
import Cs2263.Project.listable.tasks.ParentTask;

import java.util.LinkedList;

public class Section extends ListArchetype implements ListableItem {
    // Variables
    // Listable type
    private static final ListableType type = ListableType.Section;
    // Instance variables
    private LinkedList<ParentTask> tasks;

    // Constructor
    public Section(){

    }
    public LinkedList<ParentTask> getTasks() {
        return tasks;
    }

    @Override
    public ListableType getType() {
        return null;
    }
}
