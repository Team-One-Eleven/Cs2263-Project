/**
 * The To Do list class
 *
 * Makes up both the basic lists, and the sublists.
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

import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.listable.ListableType;
import Cs2263.Project.listable.lists.ListArchetype;
import Cs2263.Project.listable.lists.Section;

import java.util.LinkedList;

public class ToDoList extends ListArchetype implements ListableItem {
    // Variables
    // Listable type
    private static final ListableType type = ListableType.List;
    // Instance variables
    private LinkedList<String> comments;
    private Section defaultSection;
    private LinkedList<Section> sections;
    private boolean archived;

    // Constructor
    public ToDoList(){
        archived = false;
        comments = new LinkedList<>();
        defaultSection = null;
        sections = new LinkedList<>();
    }

    //Methods
    // GETTERS
    public LinkedList<String> getComments() {
        return comments;
    }
    public Section getDefaultSection() {
        return defaultSection;
    }
    public LinkedList<Section> getSections() {
        return sections;
    }
    public boolean isArchived() {
        return archived;
    }
    // Setters
    public void setArchived(boolean archived) {
        this.archived = archived;
    }
    // ListableItem Implementation
    @Override
    public ListableType getType() {
        return type;
    }
}
