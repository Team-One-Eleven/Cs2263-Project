/**
 * Controller for the dialog box that requests the first and last name from the user
 *
 * @Author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.UI.Commands.AddFirstLastNameCommand;
import Cs2263.UI.UIManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstLastDialogController extends UIViewController {

    @FXML TextField fxFirstName;
    @FXML TextField fxLastName;
    @FXML Button fxFirstLastOK;
    @FXML Label fxFirstLastLabel;
    @FXML Label fxFirstLastErrorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onOKClicked(){
        try{
            AddFirstLastNameCommand addFirstLastNameCommand = new AddFirstLastNameCommand(fxFirstName.getText(),fxLastName.getText());
            uiManager.setCommand(addFirstLastNameCommand);
            uiManager.executeCommand();
        }
        catch (IllegalArgumentException e){
            fxFirstLastErrorLabel.setText("Alphabetic Characters only");
        }

    }

    private void closeDialog(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
