/**
 * The List Abstract class
 *
 * provides the common structure among the 2 types of lists
 *
 *
 * @author  Traae
 * @version 1.0
 * @since 3/31/2021
 */


package Cs2263.Project.listable.lists;
import java.util.LinkedList;

public abstract class ListArchetype {
    // Variables
    private String title;
    private String description;

    public ListArchetype(){
        title = "";
        description = "";

    }


    // Methods
    // GETTERS
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    // SETTERS
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
