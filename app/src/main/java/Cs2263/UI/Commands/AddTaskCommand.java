/**
 * Adds a task to the master list given context on what is selected
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Commands;

import Cs2263.Project.Orchestrator;
import Cs2263.Project.listable.lists.ToDoList;

import java.io.IOException;
import java.util.LinkedList;

public class AddTaskCommand implements UICommand{

    Orchestrator orchestrator;

    //TODO Complete Iterative search and add functionality


    public AddTaskCommand(){
        try {
            orchestrator = Orchestrator.getInstance();
        }
        catch (IOException io){
            System.out.println("An Orchestrator IO Exception Occurred in AddTaskCommand");
        }
        LinkedList<ToDoList> itemList = orchestrator.getMasterList();

        for(ToDoList t : itemList){

        }
    }

    @Override
    public void execute() {

    }
}
