/**
 * This command communicates with the orchestrator class and the UI to login the user.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Commands;

import Cs2263.Project.Orchestrator;
import Cs2263.UI.Controllers.HomeUIViewController;
import Cs2263.UI.Controllers.LoginUIViewController;
import Cs2263.UI.UIManager;
import javax.security.auth.login.FailedLoginException;
import java.io.IOException;

public class LoginUserCommand implements UICommand{

    private final String email;
    private final String password;
    private HomeUIViewController homeUIViewController = UIManager.getInstance().getView().getHomeController();
    private LoginUIViewController loginUIViewController = UIManager.getInstance().getView().getLoginController();
    private Orchestrator orchestrator;


    /**
     * Creates a login user command instance which simply stores an email and password for use in login
     *
     * @param e  User Email
     * @param p  User Password
     */
    public LoginUserCommand(String e, String p){
        try {
            orchestrator = Orchestrator.getInstance();
        }
        catch (IOException io){
            System.out.println("An Orchestrator IO Exception Occurred in LoginUserCommand");
        }
       this.email = e;
       this.password =p;
    }

    /**
     * Sends email and password data to orchestrator for login.
     */

    @Override
    public void execute(){
        try{
            orchestrator.loginUser(email,password);
            homeUIViewController.loadUserInfo();
        }
        catch (IOException e){
            loginUIViewController.setLoginLabel("IO Exception Occurred");
        }
        catch (FailedLoginException e){
            loginUIViewController.setLoginLabel("Invalid credentials");
        }
    }
}
