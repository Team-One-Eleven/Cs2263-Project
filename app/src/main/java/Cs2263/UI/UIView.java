/**
 * The UI view loads the FXML for the UI, sets the scene and shows the window to the user.
 * @author Braydon Spaulding
 */

package Cs2263.UI;

import Cs2263.UI.Controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UIView extends Application {
    private final int WIDTH = 692;
    private final int HEIGHT = 409;

    private final String UI_HOME_DOC = "/HomeUI.fxml";
    private final String UI_LOGIN_DOC = "/LoginUI.fxml";
    private final String UI_REGISTER_DOC = "/RegisterUI.fxml";

    private HomeUIViewController homeUIViewController;
    private LoginUIViewController loginUIViewController;
    private RegisterUIViewController registerUIViewController;

    private Scene homeScene;
    private Scene loginScene;
    private Scene registerScene;

    public UIView(){

    }


    @Override
    public void start(Stage primaryStage) throws Exception {


        //Load each FXML file for different UI states
        FXMLLoader homeFxmlLoader = new FXMLLoader(getClass().getResource(UI_HOME_DOC));
        FXMLLoader loginFxmlLoader = new FXMLLoader(getClass().getResource(UI_LOGIN_DOC));
        FXMLLoader registerFxmlLoader = new FXMLLoader(getClass().getResource(UI_REGISTER_DOC));

        //Load the FXML docs for the different scenes
        Parent homePageParent = homeFxmlLoader.load();
        Parent loginPageParent = loginFxmlLoader.load();
        Parent registerPageParent = registerFxmlLoader.load();

        //Load all controllers for the above elements so they can be used by the system.
        homeUIViewController = homeFxmlLoader.getController();
        System.out.println(homeUIViewController);
        loginUIViewController = loginFxmlLoader.getController();
        registerUIViewController = registerFxmlLoader.getController();

        //Load scenes
        homeScene = new Scene(homePageParent, WIDTH, HEIGHT);
        loginScene = new Scene(loginPageParent, WIDTH, HEIGHT);
        registerScene = new Scene(registerPageParent, WIDTH, HEIGHT);

        //Pass loaded scenes to UI controllers so they can change to them
        loginUIViewController.setRegisterScene(registerScene);
        loginUIViewController.setHomeScene(homeScene);
        registerUIViewController.setLoginScene(loginScene);

        primaryStage.setTitle("TODO");

        //Start at login scene and show
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    public HomeUIViewController getHomeController(){
        return homeUIViewController; }
    public LoginUIViewController getLoginController(){return loginUIViewController;}

    public void launchView(){
        Application.launch();
    }
}
