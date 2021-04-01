/**
 * Controller for the list item view (when a list item is selected)
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ListContextUIController extends UIViewController {

    //Task View Fields
    @FXML
    private TextField fxNoteTitleTextField;
    @FXML private TextArea fxNoteBodyTextArea;

    //Always visible buttons
    @FXML private Button fxNewTaskButton;
    @FXML private Button fxNewSectionButton;
    @FXML private Button fxNewListButton;

    //Task Context Buttons
    @FXML private Button fxArchiveButton;
    @FXML private Button fxMoveAllButton;
    @FXML private Button fxDeleteListButton;

}