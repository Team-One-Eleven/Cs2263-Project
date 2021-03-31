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

import java.util.Date;
import java.util.LinkedList;

public class SearchEngine {
    // Variables
    // Parent orchestrator reference
    private Orchestrator orchestrator;

    // Constructor
    public SearchEngine(Orchestrator o){
        this.orchestrator = o;
    }

    // Methods
    // SEARCHES:

    // By DATE range
    // With Active user's master list
    public LinkedList<ParentTask> searchByDate(Date start, Date end){
        LinkedList<ParentTask> taskResults = new LinkedList<>();
        LinkedList<ParentTask> childTaskResults = new LinkedList<>();
        for (ToDoList list : orchestrator.getMasterList()){
            for (Section sec : list.getSections()){
                for (ParentTask task : sec.getTasks()){
                    if ((task.getDueDate().after(start)) & (task.getDueDate().before(end))){
                        taskResults.add(task);
                    }
                    else {
                        for (ChildTask c : task.getChildTasks()){
                            if ((c.getDueDate().after(start)) & (c.getDueDate().before(end))){
                                childTaskResults.add(task);
                            }
                        }
                    }
                }
            }
        }
        taskResults.addAll(childTaskResults);
        return taskResults;
    }
    // With a LinkedList of Parents Tasks past in
    public LinkedList<ParentTask> searchByDate(Date start, Date end, LinkedList<ParentTask> ListToSearch){
        LinkedList<ParentTask> taskResults = new LinkedList<>();
        LinkedList<ParentTask> childTaskResults = new LinkedList<>();
        for (ParentTask task : ListToSearch){
            if ((task.getDueDate().after(start)) & (task.getDueDate().before(end))){
                taskResults.add(task);
            }
            else {
                for (ChildTask c : task.getChildTasks()){
                    if ((c.getDueDate().after(start)) & (c.getDueDate().before(end))){
                        childTaskResults.add(task);
                    }
                }
            }
        }
        taskResults.addAll(childTaskResults);
        return taskResults;
   }

    // By LABEL
    // With Active user's master list
    public LinkedList<ParentTask> searchByLabel(String Value){
        LinkedList<ParentTask> taskResults = new LinkedList<>();
        LinkedList<ParentTask> childTaskResults = new LinkedList<>();
        for (ToDoList list : orchestrator.getMasterList()){
            for (Section sec : list.getSections()){
                for (ParentTask task : sec.getTasks()){
                    if (task.getLabels().contains(Value)){
                        taskResults.add(task);
                    }
                    else {
                        for (ChildTask c : task.getChildTasks()){
                            if (c.getLabels().contains(Value)){
                                childTaskResults.add(task);
                            }
                        }
                    }
                }
            }
        }
        taskResults.addAll(childTaskResults);
        return taskResults;
    }
    // With a LinkedList of Parents Tasks past in
    public LinkedList<ParentTask> searchByLabel(String Value, LinkedList<ParentTask> ListToSearch){
        LinkedList<ParentTask> taskResults = new LinkedList<>();
        LinkedList<ParentTask> childTaskResults = new LinkedList<>();
        for (ParentTask task : ListToSearch){
            if (task.getLabels().contains(Value)){
                taskResults.add(task);
            }
            else {
                for (ChildTask c : task.getChildTasks()){
                    if (c.getLabels().contains(Value)){
                        childTaskResults.add(task);
                    }
                }
            }
        }
        taskResults.addAll(childTaskResults);
        return taskResults;
    }

    // By TITEL
    // With Active user's master list
    public LinkedList<ParentTask> searchByTitle(String Value){
        LinkedList<ParentTask> taskResults = new LinkedList<>();
        LinkedList<ParentTask> childTaskResults = new LinkedList<>();
        for (ToDoList list : orchestrator.getMasterList()){
            for (Section sec : list.getSections()){
                for (ParentTask task : sec.getTasks()){
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
            }
        }
        taskResults.addAll(childTaskResults);
        return taskResults;
    }
    // With a LinkedList of Parents Tasks past in
    public LinkedList<ParentTask> LinkedsearchByTitle(String Value, LinkedList<ParentTask> ListToSearch){
        LinkedList<ParentTask> taskResults = new LinkedList<>();
        LinkedList<ParentTask> childTaskResults = new LinkedList<>();
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
    public LinkedList<ParentTask> searchByPriority(TaskPriority Value){
        LinkedList<ParentTask> taskResults = new LinkedList<>();
        LinkedList<ParentTask> childTaskResults = new LinkedList<>();
        for (ToDoList list : orchestrator.getMasterList()){
            for (Section sec : list.getSections()){
                for (ParentTask task : sec.getTasks()){
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
            }
        }
        taskResults.addAll(childTaskResults);
        return taskResults;
    }
    // With a LinkedList of Parents Tasks past in
    public LinkedList<ParentTask> searchByPriority(TaskPriority Value, LinkedList<ParentTask> ListToSearch){
        LinkedList<ParentTask> taskResults = new LinkedList<>();
        LinkedList<ParentTask> childTaskResults = new LinkedList<>();
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
    public   LinkedList<ParentTask> searchByDescription(String Value){
        LinkedList<ParentTask> taskResults = new LinkedList<>();
        LinkedList<ParentTask> childTaskResults = new LinkedList<>();
        for (ToDoList list : orchestrator.getMasterList()){
            for (Section sec : list.getSections()){
                for (ParentTask task : sec.getTasks()){
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
            }
        }
        taskResults.addAll(childTaskResults);
        return taskResults;
    }
    // With a LinkedList of Parents Tasks past in
    public   LinkedList<ParentTask> searchByDescription(String Value, LinkedList<ParentTask> ListToSearch){
        LinkedList<ParentTask> taskResults = new LinkedList<>();
        LinkedList<ParentTask> childTaskResults = new LinkedList<>();
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

}
