package Cs2263.UI;

import Cs2263.UI.Commands.PrintHelloCommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.checkerframework.checker.guieffect.qual.UI;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeUIViewController implements Initializable {

    UIController uiController;


    //List view controls
    @FXML
    private Tab fxTasksButton;
    @FXML
    private Tab fxCompletedButton;
    @FXML
    private Tab fxOverdueButton;
    @FXML
    private ListView fxTaskList;
    @FXML
    private ListView fxCompletedList;
    @FXML
    private ListView fxOverdueList;

    //Search controls
    @FXML
    private TextField fxSearchTextField;
    @FXML
    private Button fxSearchButton;

    //User Controls
    @FXML
    private Button fxUserButton;
    @FXML
    private Button fxSettingsButton;

    //Task View Controls
    @FXML
    private TextField fxNoteTitleTextField;
    @FXML
    private ComboBox fxPriorityComboBox;
    @FXML
    private TextArea fxNoteBodyTextArea;
    @FXML
    private DatePicker fxDatePicker;
    @FXML
    private Button fxAddDateButton;
    @FXML
    private Button fxCompleteTaskButton;

    //Always visible buttons
    @FXML
    private Button fxNewTaskButton;
    @FXML
    private Button fxNewSectionButton;
    @FXML
    private Button fxNewListButton;

    //Task Context Buttons
    @FXML
    private Button fxEditLabelsButton;
    @FXML
    private Button fxEditListsButton;
    @FXML
    private Button fxAddSubtaskButton;
    @FXML
    private Button fxMoveButton;
    @FXML
    private Button fxDuplicateButton;
    @FXML
    private Button fxDeleteButton;

    //Item Context Gridpane
    @FXML
    private GridPane fxDataInputArea;

    //Context Buttons Gridpane
    @FXML
    private GridPane fxContextButtonArea;

    //Item View Gridpane (Buttons and Input Area)
    @FXML
    private GridPane fxItemViewGridPane;

    @FXML
    private void printHelloWorld(ActionEvent event){
        UICommand printCommand = new PrintHelloCommand();
        uiController.setCommand(printCommand);
    }

    @FXML
    private void executeCommand(ActionEvent event){
        uiController.executeCommand();
    }




    public HomeUIViewController(){

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uiController = new UIController();
    }
}
