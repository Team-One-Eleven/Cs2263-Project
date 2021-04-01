/**
 * The controller for the HomeUI FXML document. Provides functionality to the buttons, allowing them to create and send
 * command objects to the UIController.
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.user.User;
import Cs2263.UI.Commands.PrintHelloCommand;
import Cs2263.UI.Commands.UICommand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HomeUIViewController extends UIViewController {

    //Task, list, and section gridpanes
    private GridPane taskGridpane;
    private GridPane listGridpane;
    private GridPane sectionGridpane;


    //List view controls
    @FXML private Tab fxTasksButton;
    @FXML private Tab fxCompletedButton;
    @FXML private Tab fxOverdueButton;
    @FXML private ListView<String> fxTaskList;
    @FXML private ListView fxCompletedList;
    @FXML private ListView fxOverdueList;

    //Search controls
    @FXML private TextField fxSearchTextField;
    @FXML private Button fxSearchButton;

    //User Controls
    @FXML private Button fxUserButton;
    @FXML private Button fxSettingsButton;


    //Item Context Gridpane
    //@FXML private GridPane fxDataInputArea;

    //Context Buttons Gridpane
   // @FXML private GridPane fxContextButtonArea;

    //Item View Gridpane (Buttons and Input Area)
    //@FXML private GridPane fxItemViewGridPane;

    //Main window Gridpane
    @FXML private GridPane fxMainWindowGridPane;


    //Updates the Task ListView (tasks which aren't complete or overdue)
    @FXML
    private void updateTaskListView(){
        LinkedList<ToDoList> masterList = orchestrator.getMasterList();

    }

    @FXML private void showTask(){
        for (Node n : fxMainWindowGridPane.getChildren()) {
            if (n instanceof GridPane
                    && n.getId().equals("fxSectionViewGridPane")
                    || n.getId().equals("fxListViewGridPane"))
            {
                System.out.println("Test");
                ((GridPane)n).setDisable(true);
            }
        }
        fxMainWindowGridPane.add(taskGridpane,1,1);
    }
    @FXML private void showSection(){
        fxMainWindowGridPane.add(sectionGridpane,1,1);
    }

    @FXML private void showList(){
        fxMainWindowGridPane.add(listGridpane,1,1);
    }


    public void loadUserInfo(){

    }


    public HomeUIViewController(){

    }

    public void setTaskGridpane(GridPane g){
        this.taskGridpane = g;
    }

    public void setListGridpane(GridPane g){
        this.listGridpane = g;
    }

    public void setSectionGridpane(GridPane g){
        this.sectionGridpane = g;
    }


}
