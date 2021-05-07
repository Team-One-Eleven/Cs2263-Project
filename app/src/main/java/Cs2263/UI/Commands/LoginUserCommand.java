/**
 * This command communicates with the orchestrator class and the UI to login the user.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Commands;
import Cs2263.UI.Controllers.LoginUIViewController;
import javax.security.auth.login.FailedLoginException;

public class LoginUserCommand implements UICommand{

    private final String email;
    private final String password;
    private LoginUIViewController loginUIViewController;


    /**
     * Creates a login user command instance which simply stores an email and password for use in login
     *
     * @param e  User Email
     * @param p  User Password
     */
    public LoginUserCommand(String e, String p, LoginUIViewController l){
        this.email = e;
        this.password =p;
        this.loginUIViewController = l;
    }

    /**
     * Sends email and password data to orchestrator for login.
     */
    @Override
    public void execute(){
        try{
            orchestrator.loginUser(email,password);
        }
        catch (FailedLoginException e){
            loginUIViewController.setLoginLabel(e.getMessage());
            System.out.printf("%s at %s%n",e.toString(),this.getClass().getName());
        }
    }
}
