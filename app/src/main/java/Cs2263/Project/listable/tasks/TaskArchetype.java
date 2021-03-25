/**
 * The List Abstract class
 *
 * provides the common structure among the 2 types of lists
 *
 * STILL DEVELOPING
 * I need to resolve my constructor's across the inheritence Heirarchy and their factory.
 *
 * @author  Traae
 * @version 0.0
 * @since 3/25/2021
 */

package Cs2263.Project.listable.tasks;

import Cs2263.Project.listable.lists.ListArchetype;
import javafx.concurrent.Task;

import java.util.LinkedList;

public abstract class TaskArchetype {
    // Variables
    private String title;
    private String description;
    private String dueDate;
    private TaskPriority priority;
    private TaskStatus status;
    private LinkedList<String> labels;

    // Methods
    // GETTERS
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getDueDate() {
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
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
