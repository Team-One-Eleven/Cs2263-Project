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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;


public class UIView extends Application {
    private int WIDTH = 692;
    private int HEIGHT = 409;

    private String UI_HOME_DOC = "/HomeUI.fxml";
    private String UI_LOGIN_DOC = "/LoginUI.fxml";
    private String UI_REGISTER_DOC = "/RegisterUI.fxml";

    private String UI_TASK_GRIDPANE = "/TaskItemContext.fxml";
    private String UI_LIST_GRIDPANE = "/ListItemContext.fxml";
    private String UI_SECTION_GRIDPANE = "/SectionItemContext.fxml";

    private HomeUIViewController homeUIViewController;
    private LoginUIViewController loginUIViewController;
    private RegisterUIViewController registerUIViewController;
    private TaskContextUIController taskContextUIController;
    private ListContextUIController listContextUIController;
    private SectionContextUIController sectionContextUIController;

    private Scene homeScene;
    private Scene loginScene;
    private Scene registerScene;

    private GridPane taskContextGridpane;
    private GridPane listContextGridpane;
    private GridPane sectionContextGridpane;

    private Stage primaryStage;




    public UIView(){

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        //Load each FXML file for different UI states
        FXMLLoader homeFxmlLoader = new FXMLLoader(getClass().getResource(UI_HOME_DOC));
        FXMLLoader loginFxmlLoader = new FXMLLoader(getClass().getResource(UI_LOGIN_DOC));
        FXMLLoader registerFxmlLoader = new FXMLLoader(getClass().getResource(UI_REGISTER_DOC));

        //Load each FXML file for different item states(task, list, section) within the Home (or logged in) state
        FXMLLoader taskContentFxmlLoader = new FXMLLoader(getClass().getResource(UI_TASK_GRIDPANE));
        FXMLLoader listContentFxmlLoader = new FXMLLoader(getClass().getResource(UI_LIST_GRIDPANE));
        FXMLLoader sectionContentFxmlLoader = new FXMLLoader(getClass().getResource(UI_SECTION_GRIDPANE));

        //Load the FXML docs for the different scenes
        Parent homePageParent = homeFxmlLoader.load();
        Parent loginPageParent = loginFxmlLoader.load();
        Parent registerPageParent = registerFxmlLoader.load();

        //Load various item context Gridpanes (Task, List, Section) for use in the Home Page Scene
        taskContextGridpane = taskContentFxmlLoader.load();
        listContextGridpane = listContentFxmlLoader.load();
        sectionContextGridpane = sectionContentFxmlLoader.load();

        //Load all controllers for the above elements so they can be used by the system.
        homeUIViewController = (HomeUIViewController) homeFxmlLoader.getController();
        loginUIViewController = (LoginUIViewController) loginFxmlLoader.getController();
        registerUIViewController = (RegisterUIViewController) registerFxmlLoader.getController();
        taskContextUIController = (TaskContextUIController) taskContentFxmlLoader.getController();
        listContextUIController = (ListContextUIController) listContentFxmlLoader.getController();
        sectionContextUIController = (SectionContextUIController) sectionContentFxmlLoader.getController();

        //Load scenes
        homeScene = new Scene(homePageParent, WIDTH, HEIGHT);
        loginScene = new Scene(loginPageParent, WIDTH, HEIGHT);
        registerScene = new Scene(registerPageParent, WIDTH, HEIGHT);

        //Pass loaded scenes to UI controllers so they can change to them
        loginUIViewController.setRegisterScene(registerScene);
        loginUIViewController.setHomeScene(homeScene);

        registerUIViewController.setLoginScene(loginScene);

        //Pass loaded gridpanes to the home view so it can use them to render list items
        homeUIViewController.setTaskGridpane(taskContextGridpane);
        homeUIViewController.setListGridpane(listContextGridpane);
        homeUIViewController.setSectionGridpane(sectionContextGridpane);

        primaryStage.setTitle("Sticky Note");

        //Start at login scene and show
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public UIViewController getHomeController(){
        return homeUIViewController;
    }

    public void startUI(){
        Application.launch();
    }
}
