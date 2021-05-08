/**
 * Controller for the list item view (when a list item is selected)
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;
import Cs2263.Project.listable.lists.ToDoList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ListContextUIController extends UIViewController {

    //List View Fields
    @FXML private TextField fxNoteTitleTextField;
    @FXML private TextArea fxNoteBodyTextArea;

    //List Context Buttons
    @FXML private Button fxArchiveButton;
    @FXML private Button fxMoveAllButton;
    @FXML private Button fxDeleteListButton;
    @FXML private Button fxSaveListButton;

    //Active item
    ToDoList list;

    //Home Controller
    HomeUIViewController homeUIViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML public void setData(ToDoList item, String title, String body, Boolean isArchived){
        if(item != null){
            this.list = item;
        }
        fxNoteTitleTextField.setText(title);
        fxNoteBodyTextArea.setText(body);
        if(isArchived){
            fxArchiveButton.setVisible(false);
        }
    }

    @FXML private void saveList(){
        if(this.list == null){return;}
        list.setTitle(fxNoteTitleTextField.getText());
        list.setDescription(fxNoteBodyTextArea.getText());
        homeUIViewController.refreshTree();
    }

    @FXML private void removeList(){
        homeUIViewController.removeList();
    }

    public void setHomeUIViewController(HomeUIViewController c){
        this.homeUIViewController = c;
    }
}
