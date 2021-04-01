package Cs2263.UI.Commands;

import Cs2263.UI.Controllers.HomeUIViewController;
import Cs2263.UI.Controllers.LoginUIViewController;
import Cs2263.UI.UIManager;

import javax.security.auth.login.FailedLoginException;
import java.io.IOException;

public class LoginUserCommand implements UICommand{

    private String email;
    private String password;
    private HomeUIViewController homeUIViewController = UIManager.getInstance().getView().getHomeController();
    private LoginUIViewController loginUIViewController = UIManager.getInstance().getView().getLoginController();

    public LoginUserCommand(String e, String p){
       this.email = e;
       this.password =p;
    }

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
