/**
 * The To Do list class
 *
 * Makes up todolist / project super structure, that then contains
 * sections, which then contain tasks .
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
import Cs2263.Project.listable.lists.Section;

import java.io.Serializable;
import java.util.LinkedList;

public class ToDoList extends ListArchetype implements ListableItem, Serializable {
    // Variables
    // Listable type
    private static final ListableType type = ListableType.List;
    // Instance variables
    private LinkedList<String> comments;
    private Section defaultSection;
    private LinkedList<Section> sections;
    private boolean archived;

    // Constructor
    // NO ARGUMENTS for Serializable
    public ToDoList(){
        comments = new LinkedList<>();
        sections = new LinkedList<>();
        archived = false;
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

    public void initDefaultSection(Section s) {
        if (defaultSection == null) {
            this.defaultSection = s;
        }

    }

    // ListableItem Implementation
    @Override
    public ListableType getType() {
        return type;
    }
}
