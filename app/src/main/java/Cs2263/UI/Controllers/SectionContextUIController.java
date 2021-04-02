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

    /**
     * Below is all the JavaFX objects defined in the FXML documents for the section context of the UI.
     * This is a JavaFX controller bound to the SectionContext gridpane, which is designed to show the details of a
     * section and make available actions like deleting the section or changing its parent.
     */

    //Task View Fields
    @FXML
    private TextField fxNoteTitleTextField;
    @FXML private TextArea fxNoteBodyTextArea;


    //Task Context Buttons
    @FXML private Button fxMoveSectionButton;
    @FXML private Button fxDeleteSectionButton;

}
