/**
 * The controller for the LoginUI FXML document. Provides functionality to the buttons, allowing them to create and send
 * command objects to the UIController.
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.Project.Orchestrator;
import Cs2263.Project.tools.UserFactory;
import Cs2263.UI.Commands.LoginUserCommand;
import Cs2263.UI.Commands.RegisterUserCommand;
import Cs2263.UI.Commands.UICommand;
import Cs2263.UI.UIManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

public class RegisterUIViewController extends UIViewController {

    //Login Scene (to go back to it with the back button)
    Scene loginScene;

    //Error Label
    @FXML private Label fxRegisterLabel;

    //Login data fields
    @FXML private TextField fxRegisterEmailField;
    @FXML private TextField fxRegisterPassField;
    @FXML private TextField fxRegisterFirstNameField;
    @FXML private TextField fxRegisterLastNameField;

    //Login buttons
    @FXML private Button fxSendRegisterButton;
    @FXML private Button fxRegisterBackButton;

    public RegisterUIViewController(){

    }

    @FXML private void sendRegister(){
       try {
           String email = fxRegisterEmailField.getText();
           String password = fxRegisterPassField.getText();
           String fName = fxRegisterFirstNameField.getText();
           String lName = fxRegisterLastNameField.getText();
           RegisterUserCommand register = new RegisterUserCommand(email, password, fName, lName);
           uiManager.setCommand(register);
           uiManager.executeCommand();
       }
       catch (IllegalArgumentException e){
            setRegisterLabel("Invalid Registration Details");
       }
    }

    public void setRegisterLabel(String l){
        fxRegisterLabel.setText(l);
    }

    @FXML private void goToLogin(ActionEvent event){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
                s.setScene(loginScene);
            }
        });
    }



    public void setLoginScene(Scene s) {
        this.loginScene = s;
    }

}
