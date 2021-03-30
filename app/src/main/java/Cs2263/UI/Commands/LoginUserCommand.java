package Cs2263.UI.Commands;

import Cs2263.Project.user.User;
import Cs2263.UI.UIManager;

import java.util.HashMap;

public class LoginUserCommand implements UICommand{

    private String email;
    private String password;

    public LoginUserCommand(String e, String p){
       this.email = e;
       this.password =p;
    }

    @Override
    public void execute() {
        HashMap userList = UIManager.getInstance().getUserList();
        if(userList.containsKey(email)){
            String foundUserPass = (String) userList.get(email);
            if(foundUserPass.equals(password)){
                UIManager.getInstance().setActiveUser(email);
            }
        }
    }
}
