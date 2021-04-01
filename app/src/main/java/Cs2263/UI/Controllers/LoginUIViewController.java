/**
 * The controller for the LoginUI FXML document. Provides functionality to the buttons, allowing them to create and send
 * command objects to the UIController.
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.UI.Commands.LoginUserCommand;
import Cs2263.UI.Commands.UICommand;
import Cs2263.UI.UIManager;
import Cs2263.UI.UIView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginUIViewController extends UIViewController {

    private Scene registerScene;
    private Scene homeScene;
    //Login data fields
    @FXML private TextField fxEmailTextField;
    @FXML private TextField fxPasswordTextField;

    //Login buttons
    @FXML private Button fxLoginButton;
    @FXML private Button fxRegisterButton;

    //Register

    public LoginUIViewController(){

    }

    @FXML private void login(ActionEvent event){
        String email = fxEmailTextField.getText();
        String password = fxPasswordTextField.getText();
        UICommand login = new LoginUserCommand(email,password);
        uiManager.setCommand(login);
        uiManager.executeCommand();
        if(UIManager.getInstance().getActiveUser() != null){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    s.setScene(homeScene);
                }
            });
        }
    }

    @FXML private void goToRegisterMenu(ActionEvent event){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
                s.setScene(registerScene);
            }
        });
    }

    public void setRegisterScene(Scene s){
        registerScene = s;
    }

    public void setHomeScene(Scene s){
        homeScene = s;
    }

    @FXML private void changeLoginButton(ActionEvent event){
        fxLoginButton.setText("Hello");
    }


}