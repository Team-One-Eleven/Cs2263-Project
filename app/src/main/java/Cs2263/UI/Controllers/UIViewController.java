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

import java.net.URL;
import java.util.ResourceBundle;

public abstract class UIViewController implements Initializable {

    protected UIManager uiManager;
    protected Orchestrator Orchestrator;

    protected UIViewController() {
        uiManager = UIManager.getInstance();
        Orchestrator = uiManager.getOrchestrator();
    }

    /**
     * Required for JavaFX to bind to the controllers
     *
     * @param location  Not used at the moment
     * @param resources  Not used at the moment
     */


    public abstract void initialize(URL location, ResourceBundle resources);

    //A shorthand executeCommand for use within the UI
    @FXML private void executeCommand(ActionEvent event){
        uiManager.executeCommand();
    }

}
