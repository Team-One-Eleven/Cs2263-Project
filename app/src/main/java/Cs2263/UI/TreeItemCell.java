package Cs2263.UI;

import Cs2263.Project.listable.ListableType;
import Cs2263.Project.listable.lists.ToDoList;
import javafx.scene.control.TreeCell;

public class TreeItemCell<ListableItem> extends TreeCell<Cs2263.Project.listable.ListableItem> {


    @Override
    protected void updateItem(Cs2263.Project.listable.ListableItem item, boolean empty){
        super.updateItem(item, empty);
        if(empty){
            setGraphic(null);
        }
        else if (item.getType() == ListableType.List){
            setText(item.getTitle());
            ToDoList list = (ToDoList) item;

        }
    }
}
