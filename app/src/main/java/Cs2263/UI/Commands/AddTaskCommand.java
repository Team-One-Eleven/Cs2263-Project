/**
 * Adds a task to the master list given context on what is selected
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Commands;

import Cs2263.Project.listable.lists.ToDoList;
import java.util.LinkedList;

public class AddTaskCommand implements UICommand{

    //TODO Complete Iterative search and add functionality

    public AddTaskCommand(){

        LinkedList<ToDoList> itemList = orchestrator.getMasterList();

        for(ToDoList t : itemList){

        }
    }

    @Override
    public void execute() {

    }
}
