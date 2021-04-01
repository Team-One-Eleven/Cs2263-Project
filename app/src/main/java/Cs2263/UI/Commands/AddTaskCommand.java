package Cs2263.UI.Commands;

import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.listable.lists.ToDoList;

import java.util.LinkedList;

public class AddTaskCommand implements UICommand{


    public AddTaskCommand(){
        LinkedList<ToDoList> itemList = orchestrator.getMasterList();

        for(ToDoList t : itemList){

        }
    }

    @Override
    public void execute() {

    }
}
