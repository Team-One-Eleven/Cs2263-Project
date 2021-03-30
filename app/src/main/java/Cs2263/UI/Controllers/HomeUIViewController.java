/**
 * The controller for the HomeUI FXML document. Provides functionality to the buttons, allowing them to create and send
 * command objects to the UIController.
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.UI.Commands.PrintHelloCommand;
import Cs2263.UI.Commands.UICommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

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
    @FXML private ListView fxTaskList;
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

    @FXML
    private void printHelloWorld(ActionEvent event){
        UICommand printCommand = new PrintHelloCommand();
        uiManager.setCommand(printCommand);
    }

    @FXML
    private void updateListView(List l){

    }

    private void showTask(){

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
