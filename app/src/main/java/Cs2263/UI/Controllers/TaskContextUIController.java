/**
 * Controller for the task item view (when a task item is selected)
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;
import Cs2263.Project.listable.tasks.TaskArchetype;
import Cs2263.Project.listable.tasks.TaskPriority;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskContextUIController extends UIViewController{

    /**
     * Below is all the JavaFX objects defined in the FXML documents for the task context of the UI.
     * This is a JavaFX controller bound to the TaskContext gridpane, which is designed to show the details of a
     * task and make available actions like completing the task or chaning its due date.
     */

    //Task View Fields
    @FXML private TextField fxNoteTitleTextField;
    @FXML private TextArea fxNoteBodyTextArea;

    //Task View Controls
    @FXML private ComboBox fxPriorityComboBox;
    @FXML private DatePicker fxDatePicker;
    @FXML private Button fxAddDateButton;
    @FXML private Button fxCompleteTaskButton;

    //Task Context Buttons
    @FXML private Button fxEditLabelsButton;
    @FXML private Button fxAddSubtaskButton;
    @FXML private Button fxMoveTaskButton;
    @FXML private Button fxDeleteTaskButton;
    @FXML private Button fxSaveTaskButton;

    //Active item
    TaskArchetype task;

    //Home Controller
    HomeUIViewController homeUIViewController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxPriorityComboBox.getItems().addAll(TaskPriority.Low,TaskPriority.Medium,TaskPriority.High,TaskPriority.Highest);
    }

    @FXML public void setData(TaskArchetype item, String title, String body){
        if(item != null){
            this.task = item;
        }
        fxNoteTitleTextField.setText(title);
        fxNoteBodyTextArea.setText(body);
        fxPriorityComboBox.getSelectionModel().select(task.getPriority());
    }

    @FXML private void saveTask(){
        if(this.task == null){return;}
        task.setTitle(fxNoteTitleTextField.getText());
        task.setDescription(fxNoteBodyTextArea.getText());
        task.setPriority((TaskPriority) fxPriorityComboBox.getSelectionModel().getSelectedItem());
        homeUIViewController.refreshTree();
    }

    @FXML private void removeTask(){
        homeUIViewController.removeTask();
    }


    public void setHomeUIViewController(HomeUIViewController c){
        this.homeUIViewController = c;
    }

}
