/**
 * UI Controller designed to assist the view controllers (Home and Login) in managing the UI display.
 * Calls and provides context for commands.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI;

import Cs2263.Project.Orchestrator;
import Cs2263.UI.Commands.UICommand;

import java.io.IOException;

public class UIManager {

    private UICommand currentCommand;
    private static UIManager uiManager = null;
    private UIView uiView;
    private Orchestrator orchestrator;

    //Tries to load the orchestrator for use across the application in constructor
    private UIManager(){
//        try {
//            orchestrator = Orchestrator.getInstance();
//        }
//       catch(IOException e){
//           System.out.printf("An Orchestrator IO Exception Occurred in %s. The orchestrator could not " +
//                  "be loaded.%n", this.getClass().getName());
//        }
//       finally {
//           System.out.println(orchestrator);
//       }
    }

    public static UIManager getInstance(){
        if(uiManager == null){
            uiManager = new UIManager();

        }
        return uiManager;
    }

    /**
     * Takes a UICommand and sets it as the current command. Will run with executeCommand()
     *
     * @param u A UICommand object
     */
    public void setCommand(UICommand u){
        this.currentCommand = u;
    }

    public void executeCommand(){
        currentCommand.execute();
    }

    /**
     * Starts the UI by creating a new view and calling startUI() on the view which launches the view.
     */

    public void startUI(){
        uiView = new UIView();
        uiView.launchView();
    }

    public UIView getView(){
        return uiView;
    }

    public Orchestrator getOrchestrator(){
        return this.orchestrator;
    }



}
