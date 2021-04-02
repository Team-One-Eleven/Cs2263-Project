/**
 * Abstract UI Controller class for ensuring other controllers have an instance of uiManager
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.Project.Orchestrator;
import Cs2263.UI.UIManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class UIViewController implements Initializable {

    UIManager uiManager;
    Orchestrator orchestrator;

    protected UIViewController() {
        try{
            orchestrator = Orchestrator.getInstance();
        }
        catch (IOException io){
            System.out.println("An Orchestrator IO Exception Occurred in UIViewController");
        }
    }

    /**
     * Required for JavaFX to bind to the controllers
     *
     * @param location  Not used at the moment
     * @param resources  Not used at the moment
     */


    public void initialize(URL location, ResourceBundle resources)  {
        uiManager = UIManager.getInstance();
    }

    //A shorthand executeCommand for use within the UI
    @FXML private void executeCommand(ActionEvent event){
        uiManager.executeCommand();
    }

}
