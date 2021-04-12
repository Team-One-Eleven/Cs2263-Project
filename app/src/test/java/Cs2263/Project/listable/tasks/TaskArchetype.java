/**
 * The List Abstract class
 *
 * provides the common structure among the 2 types of lists
 *
 * @author  Traae
 * @version 1.0
 * @since 3/31/2021
 */

package Cs2263.Project.listable.tasks;

import java.util.Date;
import java.util.LinkedList;

public abstract class TaskArchetype {
    // Variables
    private String title;
    private String description;
    private boolean usingDuedate;
    private Date dueDate;
    private TaskPriority priority;
    private TaskStatus status;
    private LinkedList<String> labels;

    public TaskArchetype(){
        title = "";
        description = "";
        dueDate = new Date();
        dueDate.setTime(0);
        status = TaskStatus.incomplete;
        labels = new LinkedList<>();
    }

    // Methods
    // GETTERS
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public boolean isUsingDuedate() {
        return usingDuedate;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public TaskPriority getPriority() {
        return priority;
    }
    public TaskStatus getStatus() {
        return status;
    }
    public LinkedList<String> getLabels() {
        return labels;
    }
    // SETTERS
    public void setTitle(String title) {
        this.title = title;
    }
    public void setUsingDuedate(boolean usingDuedate) {
        this.usingDuedate = usingDuedate;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return getTitle();
    }

}
