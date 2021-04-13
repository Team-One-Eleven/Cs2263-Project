/**
 * Controller for the section item view (when a section item is selected)
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;
import Cs2263.Project.listable.lists.Section;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class SectionContextUIController extends UIViewController {

    /**
     * Below is all the JavaFX objects defined in the FXML documents for the section context of the UI.
     * This is a JavaFX controller bound to the SectionContext gridpane, which is designed to show the details of a
     * section and make available actions like deleting the section or changing its parent.
     */

    //Section View Fields
    @FXML
    private TextField fxNoteTitleTextField;
    @FXML private TextArea fxNoteBodyTextArea;


    //Section Context Buttons
    @FXML private Button fxMoveSectionButton;
    @FXML private Button fxDeleteSectionButton;
    @FXML private Button fxSaveSectionButton;

    //Active item
    Section section;

    //Home Controller
    HomeUIViewController homeUIViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML public void setData(Section item, String title, String body){
        if(item != null){
            this.section = item;
        }
        fxNoteTitleTextField.setText(title);
        fxNoteBodyTextArea.setText(body);
    }

    @FXML private void saveSection(){
        if(this.section == null){return;}
        section.setTitle(fxNoteTitleTextField.getText());
        section.setDescription(fxNoteBodyTextArea.getText());
        System.out.println(section.getTitle());
        homeUIViewController.refreshTree();
    }

    public void setHomeUIViewController(HomeUIViewController c){
        this.homeUIViewController = c;
    }
}
