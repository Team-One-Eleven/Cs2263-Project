/**
 * Search Engine Test class
 *
 * This set of tests need something to search, so they rely heavily on the Orchestrator's
 * methods that create examples. Some of these tests are completely dead if thought functions change.
 *
 * TODO copy the orchestrator's functions, improve them and use the test @before setup.
 *
 * @author  Traae
 * @version 1.0
 * @since 3/31/2021
 */

package Cs2263.Project.tools;

import Cs2263.Project.Orchestrator;
import Cs2263.Project.OrchestratorTest;
import Cs2263.Project.User;
import Cs2263.Project.listable.UserCredentials;
import Cs2263.Project.listable.lists.Section;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.listable.tasks.ChildTask;
import Cs2263.Project.listable.tasks.ParentTask;
import Cs2263.Project.listable.tasks.TaskPriority;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.FailedLoginException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchEngineTest {
//    private Orchestrator o;
//    boolean isInit = false;
//
//
//    private void initTestResources(){
//        o = Orchestrator.getInstance();
//
//        String testEmail = "SEtest@test.com";
//        String testPassword = "SEpassword";
//        String testName = "Boboru";
//
//        if (!isInit){
//            o.registerUser(testEmail, testPassword);
//            try {
//                o.loginUser(testEmail, testPassword);
//                o.getActiveUser().setFirstName(testName);
//                o.getActiveUser().setLastName(testName);
//
//                o.getActiveUser().getTheLists().add(o.makeAnExampleListStructure(5));
//                o.getActiveUser().getTheLists().add(o.makeAnExampleListStructure(100));
//                o.getActiveUser().getTheLists().add(o.makeAnExampleListStructure(57));
//
//            } catch (FailedLoginException e){
//                System.out.println("Inside init test resources for the Search engine tests \n" +
//                        " couldn't register the test user");
//            }
//
//        }
//
//
//
//
//
//    }
//
////    // By DATE range
////    // With Active user's master list
////    @Test public void searchByDateTEST(){
////        initTestResources();
////
////        // the Orcastrator's example list function has a couple Item's set to July 4th,
////        // and we used that for the user we initialized in this class.
////        Calendar start = Calendar.getInstance();
////        start.set(2021, Calendar.JULY, 3);
////        Calendar end = Calendar.getInstance();
////        end.set(2021, Calendar.JULY, 5);
////
////        ArrayList<ParentTask> results = o.getSearchEngine().searchByDate(start, end);
////
////        // the example list function has the 4th july on a couple items.
////        // using that for that for the master list test
////        boolean onlyJuly4th = true;
////        for (ParentTask p : results){
////            if ((p.getDueDate().after(end)) & (p.getDueDate().before(start))){
////                onlyJuly4th = false;
////            }
////        }
////        assertTrue(onlyJuly4th);
////
////        // should just return the same list
////        results = o.getSearchEngine().searchByDate(start, end, results);
////        onlyJuly4th = true;
////        for (ParentTask p : results){
////            if ((p.getDueDate().after(end)) & (p.getDueDate().before(start))){
////                onlyJuly4th = false;
////            }
////        }
////        assertTrue(onlyJuly4th);
////
////        // make a different date, add it to the list and then run it through once more to make sure.
////        ParentTask differentDate = o.getItemFactory().makeParentTask();
////        Calendar christmas = Calendar.getInstance();
////        christmas.set(2021, Calendar.DECEMBER, 25);
////        differentDate.setDueDate(christmas);
////        results.add(differentDate);
////        start.set(2021, Calendar.DECEMBER, 24);
////        end.set(2021, Calendar.DECEMBER, 26);
////
////        // should just return the same list
////        results = o.getSearchEngine().searchByDate(start, end, results);
////        boolean onlyXmas = true;
////        for (ParentTask p : results){
////            if ((p.getDueDate().isAfter(end)) & (p.getDueDate().before(start))){
////                onlyXmas = false;
////            }
////        }
////        assertTrue(onlyXmas);
////
////    }
//
//
//
//    @Test public void searchByTitleTEST(){
//        initTestResources();
//
//        String searchFor = "Title we are looking for";
//        ParentTask searchable1 = o.getItemFactory().makeParentTask();
//        searchable1.setTitle(searchFor);
//        ParentTask searchable2 = o.getItemFactory().makeParentTask();
//        searchable2.setTitle(searchFor);
//        ParentTask searchable3 = o.getItemFactory().makeParentTask();
//        searchable3.setTitle(searchFor);
//
//        o.getMasterList().get(0).getDefaultSection().addTask(searchable1);
//        o.getMasterList().get(1).getDefaultSection().addTask(searchable2);
//        o.getMasterList().get(-1).getSections().get(-1).addTask(searchable3);
//
//        ArrayList<ParentTask> results = o.getSearchEngine().searchByTitle(searchFor);
//
//        boolean onlyOurSearchable = true;
//        for (ParentTask p : results){
//            if (!p.getTitle().equals(searchFor)){
//                onlyOurSearchable = false;
//            }
//        }
//        assertTrue(onlyOurSearchable);
//
//        results = o.getSearchEngine().searchByTitle("Not searchFor", results);
//        boolean notOurSearchable = true;
//        for (ParentTask p : results){
//            if (p.getTitle().equals(searchFor)){
//                notOurSearchable = false;
//            }
//        }
//        assertTrue(notOurSearchable);
//
//    }
//
//
//
//    @Test public void searchByPriorityTEST(){
//        initTestResources();
//
//        ArrayList<ParentTask> results = o.getSearchEngine().searchByPriority(TaskPriority.Highest);
//        boolean onlyHighest = true;
//        for (ParentTask p : results){
//            if (p.getPriority() != TaskPriority.Highest){
//                onlyHighest = false;
//            }
//        }
//        assertTrue(onlyHighest);
//        // currently there are 2 Highest in the MakeExampleListStructure()
//        // if that changes this assertion is obsolete.
//        // I need to code this better
//        assertTrue(results.size() == 2);
//
//        // now I'll have the search engine by each priority and add them.
//        // then test the list search
//        results.addAll(o.getSearchEngine().searchByPriority(TaskPriority.Low));
//        results.addAll(o.getSearchEngine().searchByPriority(TaskPriority.Medium));
//        results.addAll(o.getSearchEngine().searchByPriority(TaskPriority.High));
//
//        results = o.getSearchEngine().searchByPriority(TaskPriority.Medium);
//        boolean onlyMed = true;
//        for (ParentTask p : results){
//            if (p.getPriority() != TaskPriority.Medium){
//                onlyMed = false;
//            }
//        }
//        assertTrue(onlyMed);
//
//    }
//
//
//    @Test public void searchByDescriptionTEST(){
//        initTestResources();
//
//        String searchFor = "description we are looking for";
//        ParentTask searchable1 = o.getItemFactory().makeParentTask();
//        searchable1.setDescription(searchFor);
//        ParentTask searchable2 = o.getItemFactory().makeParentTask();
//        searchable2.setDescription(searchFor);
//        ParentTask searchable3 = o.getItemFactory().makeParentTask();
//        searchable3.setDescription(searchFor);
//
//        o.getMasterList().get(0).getDefaultSection().addTask(searchable1);
//        o.getMasterList().get(1).getDefaultSection().addTask(searchable2);
//        o.getMasterList().get(-1).getSections().get(-1).addTask(searchable3);
//
//        ArrayList<ParentTask> results = o.getSearchEngine().searchByDescription(searchFor);
//
//        boolean onlyOurSearchable = true;
//        for (ParentTask p : results){
//            if (!p.getDescription().contains(searchFor)){
//                onlyOurSearchable = false;
//            }
//        }
//        assertTrue(onlyOurSearchable);
//
//
//    }


}
