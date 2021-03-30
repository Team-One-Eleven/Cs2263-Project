/**
 * Controller for the task item view (when a task item is selected)
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TaskContextUIController extends UIViewController{

    //Task View Fields
    @FXML private TextField fxNoteTitleTextField;
    @FXML private TextArea fxNoteBodyTextArea;

    //Task View Controls
    @FXML private ComboBox fxPriorityComboBox;
    @FXML private DatePicker fxDatePicker;
    @FXML private Button fxAddDateButton;
    @FXML private Button fxCompleteTaskButton;

    //Always visible buttons
    @FXML private Button fxNewTaskButton;
    @FXML private Button fxNewSectionButton;
    @FXML private Button fxNewListButton;

    //Task Context Buttons
    @FXML private Button fxEditLabelsButton;
    @FXML private Button fxEditListsButton;
    @FXML private Button fxAddSubtaskButton;
    @FXML private Button fxMoveTaskButton;
    @FXML private Button fxDuplicateButton;
    @FXML private Button fxDeleteTaskButton;

}
