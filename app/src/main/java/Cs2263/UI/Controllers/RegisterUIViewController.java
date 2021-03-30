/**
 * The controller for the LoginUI FXML document. Provides functionality to the buttons, allowing them to create and send
 * command objects to the UIController.
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.UI.Commands.LoginUserCommand;
import Cs2263.UI.Commands.UICommand;
import Cs2263.UI.UIManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisterUIViewController extends UIViewController {

    //Login Scene (to go back to it with the back button)
    Scene loginScene;

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
        String email = fxRegisterEmailField.getText();
        String password = fxRegisterPassField.getText();
        String fName = fxRegisterFirstNameField.getText();
        String lName = fxRegisterLastNameField.getText();
        UICommand login = new LoginUserCommand(email,password);
        uiManager.setCommand(login);
    }

    @FXML private void goToRegister(){

    }

    public void setLoginScene(Scene s) {
        this.loginScene = s;
    }

}
