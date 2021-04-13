/**
 * Item Factory Class
 *
 * produces the various listable items, and
 * sets up there basic settings
 *
 *
 * @author  Traae
 * @version 1.0
 * @since 3/31/2021
 */

package Cs2263.Project.tools;

import Cs2263.Project.Orchestrator;
import Cs2263.Project.listable.lists.Section;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.listable.tasks.ChildTask;
import Cs2263.Project.listable.tasks.ParentTask;

public class ItemFactory {
    // Variables
    private Orchestrator Orchestrator;

    // Constructor
    public ItemFactory(Orchestrator o){
        this.Orchestrator = o;
    }

    // Methods

    // make ToDoLists
    // all of these come with a default section.
    public ToDoList makeToDOList(){
        ToDoList t = new ToDoList();
        Section s = new Section();
        s.initId(Orchestrator.getActiveUser().getNextSectionID());
        t.initDefaultSection(s);
        t.getSections().add(s);

        return t;
    }

    // Make Section
    // Ensures to produce a new section with a unique section ID
    public Section makeSection(){
        Section s = new Section();
        s.initId(Orchestrator.getActiveUser().getNextSectionID());
        return s;
    }

    public ParentTask makeParentTask(){
        return new ParentTask();
    }
    public ChildTask makeChildTask(){
        return new ChildTask();
    }
}
