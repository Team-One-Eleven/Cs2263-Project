/**
 * The controller for the LoginUI FXML document. Provides functionality to the buttons, allowing them to create and send
 * command objects to the UIController.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.UI.Commands.LoginUserCommand;
import Cs2263.UI.Commands.UICommand;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginUIViewController extends UIViewController {

    private Scene registerScene;
    private Scene homeScene;


    //Login Gridpane
    @FXML private GridPane fxLoginGridpane;

    //Login error label
    @FXML private Label fxLoginLabel;

    //Login data fields
    @FXML private TextField fxEmailTextField;
    @FXML private PasswordField fxPasswordTextField;

    //Login button
    @FXML private Button fxLoginButton;

    //Register button
    @FXML private Button fxRegisterButton;

    //Image View for Logo
    @FXML private ImageView fxLoginImageView;

    //Home View Controller
    HomeUIViewController homeUIViewController;



    public LoginUIViewController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            InputStream logoInput = getClass().getClassLoader().getResourceAsStream("logo.png");
            Image logo = new Image(logoInput);
            fxLoginImageView.setImage(logo);
        }
        catch(Exception e){
            System.out.println("Failed load logo from resources folder");
        }

    }

    /**
     * Creates a login command from the form data, sends it to the uiManager, and executes it.
     * @param event  Used to change scene with button
     */

    @FXML private void login(ActionEvent event){
        String email = fxEmailTextField.getText();
        String password = fxPasswordTextField.getText();
        UICommand login = new LoginUserCommand(email,password,this);
        uiManager.setCommand(login);
        uiManager.executeCommand();
        if(orchestrator.getActiveUser() != null){
            Platform.runLater(() -> {
                fxEmailTextField.setText("");
                fxPasswordTextField.setText("");
                Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
                s.setScene(homeScene);
                homeUIViewController.loadUserTaskList();
            });
        }


    }

    @FXML private void goToRegisterMenu(ActionEvent event){
        Platform.runLater(() -> {
            fxEmailTextField.setText("");
            fxPasswordTextField.setText("");
            Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
            s.setScene(registerScene);
        });
    }

    public void setLoginLabel(String l){
        fxLoginLabel.setText(l);
    }

    public void setHomeUIViewController(HomeUIViewController c){
        this.homeUIViewController = c;
    }

    public void setRegisterScene(Scene s){
        registerScene = s;
    }

    public void setHomeScene(Scene s){
        homeScene = s;
    }


}
