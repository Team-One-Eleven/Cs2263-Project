/**
 * Search Engine class
 *
 * Takes a list for a given search condition and value and return's a new list
 * with the desired results.
 *
 * Most of these functions are very similar as then need to scan throug hteh same lists,
 * but with variations in the contitions that the use to qualify for the search results
 *
 * As of version 1.0, all parentTask's are searched and added to the first section of the list,
 * then every ChildTask that qualifies has its parentTask added after the first section.
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
import Cs2263.Project.listable.tasks.TaskPriority;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchEngine {
    // Variables
    // Parent orchestrator reference
    private Orchestrator Orchestrator;

    // Constructor
    public SearchEngine(Orchestrator o){
        this.Orchestrator = o;
    }

    // Methods
    // SEARCHES:

    // By Calendar range
    // With Active user's master list
    public ArrayList<ParentTask> searchByDateRange(LocalDate start, LocalDate end){
        ArrayList<ParentTask> taskResults = new ArrayList<>();
        for (ToDoList list : Orchestrator.getMasterList()){
            masterRecursiveByDateRange(start, end, list, taskResults);
        }
        return taskResults;
    }
    // co function that recursively scans the list
    private void masterRecursiveByDateRange(LocalDate start, LocalDate end, ToDoList toSearch, ArrayList<ParentTask> results) {
        for (Section s : toSearch.getSections()){
            for (ParentTask task : s.getTasks()){
                if ((task.getDueDate().isAfter(start)) & (task.getDueDate().isBefore(end))){
                    results.add(task);
                }
                else {
                    for (ChildTask c : task.getChildTasks()){
                        if ((c.getDueDate().isAfter(start)) & (c.getDueDate().isBefore(end))){
                            results.add(task);
                        }
                    }
                }

            }
            for (ToDoList list : s.getLists()){
                masterRecursiveByDateRange(start, end, list, results);
            }
        }
    }
    // With a ArrayList of Parents Tasks past in
    public ArrayList<ParentTask> searchByDateRange(LocalDate start, LocalDate end, ArrayList<ParentTask> ListToSearch){
        ArrayList<ParentTask> taskResults = new ArrayList<>();
        ArrayList<ParentTask> childTaskResults = new ArrayList<>();
        for (ParentTask task : ListToSearch){
            if ((task.getDueDate().isAfter(start)) & (task.getDueDate().isBefore(end))){
                taskResults.add(task);
            }
            else {
                for (ChildTask c : task.getChildTasks()){
                    if ((c.getDueDate().isAfter(start)) & (c.getDueDate().isBefore(end))){
                        childTaskResults.add(task);
                    }
                }
            }
        }
        taskResults.addAll(childTaskResults);
        return taskResults;
   }

    // By Calendar Date
    // With Active user's master list
    public ArrayList<ParentTask> searchByDate(LocalDate searchDate){
        ArrayList<ParentTask> taskResults = new ArrayList<>();
        for (ToDoList list : Orchestrator.getMasterList()){
            masterRecursiveByDate(searchDate, list, taskResults);
        }
        return taskResults;
    }
    // co function that recursively scans the list
    private void masterRecursiveByDate(LocalDate searchDate, ToDoList toSearch, ArrayList<ParentTask> results) {
        for (Section s : toSearch.getSections()){
            for (ParentTask task : s.getTasks()){
                if (task.getDueDate().isEqual(searchDate)){
                    results.add(task);
                }
                else {
                    for (ChildTask c : task.getChildTasks()){
                        if (c.getDueDate().isEqual(searchDate)){
                            results.add(task);
                        }
                    }
                }

            }
            for (ToDoList list : s.getLists()){
                masterRecursiveByDate(searchDate, list, results);
            }
        }
    }
    // With a ArrayList of Parents Tasks past in
    public ArrayList<ParentTask> searchByDate(LocalDate searchDate, ArrayList<ParentTask> ListToSearch){
        ArrayList<ParentTask> taskResults = new ArrayList<>();
        ArrayList<ParentTask> childTaskResults = new ArrayList<>();
        for (ParentTask task : ListToSearch){
            if (task.getDueDate().isEqual(searchDate)){
                taskResults.add(task);
            }
            else {
                for (ChildTask c : task.getChildTasks()){
                    if (c.getDueDate().isEqual(searchDate)){
                        childTaskResults.add(task);
                    }
                }
            }
        }
        taskResults.addAll(childTaskResults);
        return taskResults;
    }

    // By TITLE
    // With Active user's master list
    public ArrayList<ParentTask> searchByTitle(String Value){
        ArrayList<ParentTask> taskResults = new ArrayList<>();
        for (ToDoList list : Orchestrator.getMasterList()){
            masterRecursiveByTitle(Value, list, taskResults);
        }
        return taskResults;
    }
    // co function that recursively scans the list
    private void masterRecursiveByTitle(String value, ToDoList toSearch, ArrayList<ParentTask> results) {
        for (Section s : toSearch.getSections()){
            for (ParentTask task : s.getTasks()){
                if (task.getTitle() == value){
                    results.add(task);
                }
                else {
                    for (ChildTask c : task.getChildTasks()){
                        if (c.getTitle() == (value)){
                            results.add(task);
                        }
                    }
                }

            }
            for (ToDoList list : s.getLists()){
                masterRecursiveByTitle(value, list, results);
            }
        }
    }
    // With a ArrayListt of Parents Tasks past in
    public ArrayList<ParentTask> searchByTitle(String Value, ArrayList<ParentTask> ListToSearch){
        ArrayList<ParentTask> taskResults = new ArrayList<>();
        ArrayList<ParentTask> childTaskResults = new ArrayList<>();
        for (ParentTask task : ListToSearch){
            if (task.getTitle() == Value){
                taskResults.add(task);
            }
            else {
                for (ChildTask c : task.getChildTasks()){
                    if (c.getTitle() == Value){
                        childTaskResults.add(task);
                    }
                }
            }
        }
        taskResults.addAll(childTaskResults);
        return taskResults;
    }

    // By PRIORITY
    // With Active user's master list
    public ArrayList<ParentTask> searchByPriority(TaskPriority Value){
        ArrayList<ParentTask> taskResults = new ArrayList<>();
        for (ToDoList list : Orchestrator.getMasterList()){
            masterRecursiveByPriority(Value, list, taskResults);
        }
        return taskResults;
    }
    // co function that recursively scans the list
    private void masterRecursiveByPriority(TaskPriority value, ToDoList toSearch, ArrayList<ParentTask> results) {
        for (Section s : toSearch.getSections()){
            for (ParentTask task : s.getTasks()){
                if (task.getPriority() == value){
                    results.add(task);
                }
                else {
                    for (ChildTask c : task.getChildTasks()){
                        if (c.getPriority() == value){
                            results.add(task);
                        }
                    }
                }

            }
            for (ToDoList list : s.getLists()){
                masterRecursiveByPriority(value, list, results);
            }
        }
    }
    // With a ArrayListof Parents Tasks past in
    public ArrayList<ParentTask> searchByPriority(TaskPriority Value, ArrayList<ParentTask> ListToSearch){
        ArrayList<ParentTask> taskResults = new ArrayList<>();
        ArrayList<ParentTask> childTaskResults = new ArrayList<>();
        for (ParentTask task : ListToSearch){
            if (task.getPriority() == Value){
                taskResults.add(task);
            }
            else {
                for (ChildTask c : task.getChildTasks()){
                    if (c.getPriority() == Value){
                        childTaskResults.add(task);
                    }
                }
            }
        }
        taskResults.addAll(childTaskResults);
        return taskResults;
    }

    // By DESCRIPTION
    // With Active user's master list
    public   ArrayList<ParentTask> searchByDescription(String Value){
        ArrayList<ParentTask> taskResults = new ArrayList<>();
        for (ToDoList list : Orchestrator.getMasterList()){
            masterRecursiveByDescription(Value, list, taskResults);
        }
        return taskResults;
    }
    // co function that recursively scans the list
    private void masterRecursiveByDescription(String value, ToDoList toSearch, ArrayList<ParentTask> results) {
        for (Section s : toSearch.getSections()){
            for (ParentTask task : s.getTasks()){
                if (task.getDescription().contains(value)){
                    results.add(task);
                }
                else {
                    for (ChildTask c : task.getChildTasks()){
                        if (c.getDescription().contains(value)){
                            results.add(task);
                        }
                    }
                }

            }
            for (ToDoList list : s.getLists()){
                masterRecursiveByDescription(value, list, results);
            }
        }
    }
    // With a ArrayList of Parents Tasks past in
    public   ArrayList<ParentTask> searchByDescription(String Value, ArrayList<ParentTask> ListToSearch){
        ArrayList<ParentTask> taskResults = new ArrayList<>();
        ArrayList<ParentTask> childTaskResults = new ArrayList<>();
        for (ParentTask task : ListToSearch){
            if (task.getDescription().contains(Value)){
                        taskResults.add(task);
            }
            else {
                for (ChildTask c : task.getChildTasks()){
                    if (c.getDescription().contains(Value)){
                                childTaskResults.add(task);
                    }
                }
            }
        }
        taskResults.addAll(childTaskResults);
        return taskResults;
    }

    /**
     * Use of labels is currently a cut feature.
     * The label search functions have been commented out for now.
     */
//    // By LABEL
//    // With Active user's master list
//    public ArrayList<ParentTask> searchByLabel(String Value){
//        ArrayList<ParentTask> taskResults = new ArrayList<>();
//        for (ToDoList list : Orchestrator.getMasterList()){
//            masterRecursiveByLabel(Value, list, taskResults);
//        }
//        return taskResults;
//    }
//    // co function that recursively scans the list
//    private void masterRecursiveByLabel(String value, ToDoList toSearch, ArrayList<ParentTask> results) {
//        for (Section s : toSearch.getSections()){
//            for (ParentTask task : s.getTasks()){
//                if (task.getLabels().contains(value)){
//                    results.add(task);
//                }
//                else {
//                    for (ChildTask c : task.getChildTasks()){
//                        if (c.getLabels().contains(value)){
//                            results.add(task);
//                        }
//                    }
//                }
//
//            }
//            for (ToDoList list : s.getLists()){
//                masterRecursiveByLabel(value, list, results);
//            }
//        }
//    }
//    // With a ArrayList of Parents Tasks past in
//    public ArrayList<ParentTask> searchByLabel(String Value, ArrayList<ParentTask> ListToSearch){
//        ArrayList<ParentTask> taskResults = new ArrayList<>();
//        ArrayList<ParentTask> childTaskResults = new ArrayList<>();
//        for (ParentTask task : ListToSearch){
//            if (task.getLabels().contains(Value)){
//                taskResults.add(task);
//            }
//            else {
//                for (ChildTask c : task.getChildTasks()){
//                    if (c.getLabels().contains(Value)){
//                        childTaskResults.add(task);
//                    }
//                }
//            }
//        }
//        taskResults.addAll(childTaskResults);
//        return taskResults;
//    }



}
