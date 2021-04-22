/**
 * This command communicates with the orchestrator class and the UI to login the user.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Commands;

import Cs2263.UI.Controllers.HomeUIViewController;
import Cs2263.UI.Controllers.LoginUIViewController;
import Cs2263.UI.UIManager;

import javax.security.auth.login.FailedLoginException;
import java.io.IOException;

public class LoginUserCommand implements UICommand{

    private final String email;
    private final String password;
    private HomeUIViewController homeUIViewController = uiManager.getView().getHomeController();
    private final LoginUIViewController loginUIViewController = uiManager.getView().getLoginController();


    /**
     * Creates a login user command instance which simply stores an email and password for use in login
     *
     * @param e  User Email
     * @param p  User Password
     */
    public LoginUserCommand(String e, String p){
        this.email = e;
        this.password =p;
    }

    /**
     * Sends email and password data to orchestrator for login.
     */

    //TODO Fix error messages creating null pointer
    @Override
    public void execute(){
        try{
            orchestrator.loginUser(email,password);
        }
        catch (FailedLoginException e){
           // loginUIViewController.setLoginLabel(e.getMessage());
            System.out.printf("%s at %s%n",e.getMessage(),this.getClass().getName());
        }
    }
}
