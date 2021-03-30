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

    private Orchestrator orchestrator = new Orchestrator();
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

    private HashMap<String,String> userList = new HashMap<>();
    private String activeUser;

    public HashMap getUserList(){
        return userList;
    }
    public void addUser(String e, String p){
        userList.put(e,p);
    }

    public void setActiveUser(String user){
        activeUser = user;
    }
    public String getActiveUser(){
        return activeUser;
    }





    public void setCommand(UICommand u){
        this.currentCommand = u;
    }

    public void executeCommand(){
        currentCommand.execute();
    }

    public void startUI(){
        buildUserList();
        uiView = new UIView();
        uiView.startUI();
    }

    public UIView getView(){
        return uiView;
    }

    public void buildUserList(){
        userList.put("Bob@gmail.com", "123");
        userList.put("Bill@yahoo.com", "123");
        userList.put("Joe@joe.com", "123");
    }

}
