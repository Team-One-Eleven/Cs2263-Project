/**
 * Item Factory Test Class
 *
 * It's a factory that creates classes with only really hold data.
 * There's not much to test beyond, "did it create the thing."
 *
 *
 * @author  Traae
 * @version 1.0
 * @since 4/30/2021
 */

package Cs2263.Project.tools;

import Cs2263.Project.Orchestrator;
import Cs2263.Project.OrchestratorTest;
import Cs2263.Project.listable.lists.Section;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.listable.tasks.ChildTask;
import Cs2263.Project.listable.tasks.ParentTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemFactoryTest {

    @Test
    public void makeToDOListTEST(){

        Orchestrator o = Orchestrator.getInstance();

        ToDoList testList = o.getItemFactory().makeToDOList();

        assertTrue(testList != null);
        assertTrue(testList.getDefaultSection() != null);
        assertTrue(testList.getDefaultSection() == testList.getSections().get(0));

    }

    // Make Section
    // Ensures to produce a new section with a unique section ID
    @Test public void makeSectionTEST(){
        Orchestrator o = Orchestrator.getInstance();

        Section test = o.getItemFactory().makeSection();

        assertTrue(test != null);


    }

    @Test public void makeParentTaskTEST(){
        Orchestrator o = Orchestrator.getInstance();

        ParentTask test = o.getItemFactory().makeParentTask();

        assertTrue(test != null);
    }
    @Test public void makeChildTaskTEST(){
        Orchestrator o = Orchestrator.getInstance();

        ChildTask test = o.getItemFactory().makeChildTask();

        assertTrue(test != null);
    }
}
