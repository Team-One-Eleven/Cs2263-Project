/**
 * Controller for the list item view (when a list item is selected)
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.Project.listable.ListableItem;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ListContextUIController extends UIViewController {

    //Task View Fields
    @FXML private TextField fxNoteTitleTextField;
    @FXML private TextArea fxNoteBodyTextArea;

    //Task Context Buttons
    @FXML private Button fxArchiveButton;
    @FXML private Button fxMoveAllButton;
    @FXML private Button fxDeleteListButton;

    //Active item
    ListableItem list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML public void setData(ListableItem item, String title, String body, Boolean isArchived){
        fxNoteTitleTextField.setText(title);
        fxNoteBodyTextArea.setText(body);
        if(isArchived){
            fxArchiveButton.setVisible(false);
        }
    }
}
