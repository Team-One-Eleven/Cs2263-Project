package Cs2263.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UIView extends Application {
    private int width = 692;
    private int height = 409;

    private Stage primaryStage;




    public UIView(){

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("/HomeUI.fxml"));

        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("Sticky Note");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void setCurrentScene(String fxml) throws Exception{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));

        primaryStage.getScene().setRoot(pane);
        primaryStage.show();
    }

    public void startUI(){
        Application.launch();
    }
}
