/**
 * Register user command object. Checks for valid email format and adds user.
 *
 * @author Braydon Spaulding
 *
 */

package Cs2263.UI.Commands;

import Cs2263.Project.Orchestrator;
import Cs2263.Project.user.User;
import Cs2263.UI.UIManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUserCommand implements UICommand{

    String email;
    String password;
    String fName;
    String lName;

    public static final Pattern VALID_EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_PATTERN =
            Pattern.compile("^[A-Z0-9]{0,20}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_NAME_PATTERN =
            Pattern.compile("^[A-Z]{0,20}$", Pattern.CASE_INSENSITIVE);

    public RegisterUserCommand(String e, String p, String f, String l) throws IllegalArgumentException{

        Matcher emailMatcher = VALID_EMAIL_PATTERN.matcher(e);
        Matcher fNameMatcher = VALID_NAME_PATTERN.matcher(f);
        Matcher lNameMatcher = VALID_NAME_PATTERN.matcher(l);
        Matcher passwordMatcher = VALID_PASSWORD_PATTERN.matcher(p);
        if(emailMatcher.find() && fNameMatcher.find() && lNameMatcher.find() && passwordMatcher.find()){
            this.email = e;
            this.password = p;
            this.fName = f;
            this.lName = l;
            System.out.printf("regCom: %s %s %s %s",e,p,f,l);
       }
        else{
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void execute() {
        if(orchestrator.registerUser(email,password)){
            User newUser = orchestrator.getActiveUser();
            newUser.setFirstName(fName);
            newUser.setLastName(lName);
        }
        else{return;}

    }
}
