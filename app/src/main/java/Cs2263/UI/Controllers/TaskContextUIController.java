/**
 * Controller for the task item view (when a task item is selected)
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    @FXML private Button fxEditListsButton;
    @FXML private Button fxAddSubtaskButton;
    @FXML private Button fxMoveTaskButton;
    @FXML private Button fxDuplicateButton;
    @FXML private Button fxDeleteTaskButton;

}
