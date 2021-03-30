/**
 * Controller for the section item view (when a section item is selected)
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SectionContextUIController extends UIViewController {

    //Task View Fields
    @FXML
    private TextField fxNoteTitleTextField;
    @FXML private TextArea fxNoteBodyTextArea;

    //Always visible buttons
    @FXML private Button fxNewTaskButton;
    @FXML private Button fxNewSectionButton;
    @FXML private Button fxNewListButton;

    //Task Context Buttons
    @FXML private Button fxMoveSectionButton;
    @FXML private Button fxDeleteSectionButton;

}
