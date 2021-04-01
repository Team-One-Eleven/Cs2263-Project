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

    UIManager uiManager;
    Orchestrator orchestrator = Orchestrator.getInstance();

    public void initialize(URL location, ResourceBundle resources) {
        uiManager = UIManager.getInstance();
    }

    @FXML
    private void executeCommand(ActionEvent event){
        uiManager.executeCommand();
    }

}
