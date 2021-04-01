/**
 * UI Controller designed to assist the view controllers (Home and Login) in managing the UI display.
 * Calls and provides context for commands.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI;

import Cs2263.Project.Orchestrator;
import Cs2263.UI.Commands.UICommand;
import Cs2263.UI.States.LoginStates.LoginState;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class UIManager {

    private Orchestrator orchestrator = Orchestrator.getInstance();
    private UICommand currentCommand;
    private LoginState currentLoginState;
    private static UIManager uiManager = null;
    private UIView uiView;

    private UIManager(){

    }

    public static UIManager getInstance(){
        if(uiManager == null){
            uiManager = new UIManager();
        }
        return uiManager;
    }






    public void setCommand(UICommand u){
        this.currentCommand = u;
    }

    public void executeCommand(){
        currentCommand.execute();
    }

    public void startUI(){
        uiView = new UIView();
        uiView.startUI();
    }

    public UIView getView(){
        return uiView;
    }



}
