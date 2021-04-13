/**
 * List manager.
 *
 * A tool mainly needed for the function of managing the master list.
 * The master list must be constructed from a list of To Do Lists, and a list
 * of tasks, because  the tasks must be able to belong to multiple lists.
 *
 * In order to ensure there are no problems during serialization and saving,
 * they are in separate parts, and are then brought together to make the master list.
 *
 *
 * @author  Traae
 * @version 1.0
 * @since 4/6/2021
 */

package Cs2263.Project.tools;

import Cs2263.Project.OrchestratorTest;
import Cs2263.Project.listable.lists.Section;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.listable.tasks.ParentTask;
import Cs2263.Project.listable.tasks.TaskStatus;

import java.util.ArrayList;
import java.util.Date;


public class ListManagerTest {
//    private OrchestratorTest o;
//
//    public ListManagerTest(OrchestratorTest o){
//        this.o = o;
//    }
//    public void constructMasterList(){
//        /**
//         * This method take the active user's tasks, and the scans through the sections
//         * to find those that match a tasks parent Section id's then adds them to that section.
//         *
//         * If any tasks fail to find a home, then should be added to the master list under a new "unlisted section"
//         */
//        ArrayList<ToDoList> masterList = o.getMasterList();
//        User activeUser = o.getActiveUser();
//
//        masterList.addAll(activeUser.getTheLists());
//        ArrayList<ParentTask> unListed = new ArrayList<>();
//
//        for (ParentTask toAdd : activeUser.getTheTasks()){
//            for (ToDoList toSearch: masterList){
//                nextListDownAddTask(toSearch, toAdd);
//            }
//            if (!toAdd.getParentSections().isEmpty()){
//                toAdd.getParentSections().clear();
//                unListed.add(toAdd);
//            }
//
//        }
//
//        if (!unListed.isEmpty()){
//            ToDoList lostAndFound = o.getItemFactory().makeToDOList();
//            lostAndFound.setTitle("Unlisted");
//            lostAndFound.getDefaultSection().getTasks().addAll(unListed);
//            masterList.add(lostAndFound);
//        }
//
//        activeUser.getTheTasks().clear();
//
//    }
//    private void nextListDownAddTask(ToDoList toSearch, ParentTask toAdd){
//        /**
//         * This method is a private RECURSIVE helper method for the above "constructMasterList()"
//         *
//         * It is needed because there is no specified limit the depth of layers of sub-lists.
//         */
//        if (!toAdd.getParentSections().isEmpty()){
//            for (Section secSearch: toSearch.getSections()){
//                ArrayList<String> toRemove = new ArrayList<>();
//                for (String id: toAdd.getParentSections()){
//                    if (id == secSearch.getId()){
//                        secSearch.getTasks().add(toAdd);
//                        toRemove.add(id);
//                    }
//                }
//                toAdd.getParentSections().removeAll(toRemove);
//                for (ToDoList nextList : secSearch.getLists()){
//                    nextListDownAddTask(nextList, toAdd);
//                }
//            }
//        }
//    }
//
//    public void deconstructMasterList(){
//        /**
//         * This method scans through the master list, and removes all tasks from their parent sections, giving the task
//         * the id of it's parent.
//         */
//        o.getMasterList().addAll(o.getActiveUser().getTheLists());
//
//        for (ToDoList toSearch: o.getMasterList()){
//            nextListDownRemove(toSearch);
//        }
//
//
//    }
//    private void nextListDownRemove(ToDoList toSearch){
//        /**
//         * This is a private RECURSIVE helper method for the above "deconstructMasterList"
//         *
//         * if repeats the deconstruction loop for all of the possible layers of sub-lists
//         */
//        for (Section secSearch: toSearch.getSections()){
//            for (ParentTask t : secSearch.getTasks()){
//                t.getParentSections().add(secSearch.getId());
//                o.getActiveUser().getTheTasks().add(t);
//            }
//            secSearch.getTasks().clear();
//            for (ToDoList nextList : secSearch.getLists()){
//                nextListDownRemove(nextList);
//            }
//        }
//    }
//
//    public void checkDueDates(){
//        /**
//         * This method check the due dates of all tasks, and marks them overdue.
//         * This method deconstructs the master list and does not reconstruct it.
//         *
//         * Currently only being used in the orchestrator at login.
//         */
//        if (o.getActiveUser().getTheTasks().isEmpty()){
//            deconstructMasterList();
//        }
//        for (ParentTask t : o.getActiveUser().getTheTasks()){
//            if ((t.getDueDate().before(new Date())) & (t.getStatus() == TaskStatus.incomplete)){
//                t.setStatus(TaskStatus.overdue);
//            }
//        }
//    }


}
