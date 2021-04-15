/**
 * UI Controller designed to assist the view controllers (Home and Login) in managing the UI display.
 * Calls and provides context for commands.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI;

import Cs2263.Project.Orchestrator;
import Cs2263.UI.Commands.UICommand;

public class UIManager {

    private UICommand currentCommand;
    private static UIManager uiManager = null;
    private UIView uiView;
    private Orchestrator Orchestrator;

    //Tries to load the orchestrator for use across the application in constructor
    private UIManager(){
        try {
            Orchestrator = Orchestrator.getInstance();
        }catch (Exception e){
            System.out.println("EXCEPTION!!@#$!@#%!@$%!@$%^~");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }

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
        return this.Orchestrator;
    }



}