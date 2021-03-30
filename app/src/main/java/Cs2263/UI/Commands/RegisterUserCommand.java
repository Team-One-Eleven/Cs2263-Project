/**
 * Register user command object. Checks for valid email format and adds user.
 *
 * @author Braydon Spaulding
 *
 */

package Cs2263.UI.Commands;

import Cs2263.UI.UIManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterUserCommand implements UICommand{

    String email;
    String password;
    String firstName;
    String lastName;

    public static final Pattern VALID_EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public RegisterUserCommand(String e, String p){
        this.email = e;
        this.password = p;
    }

    @Override
    public void execute() {
        Matcher matcher = VALID_EMAIL_PATTERN.matcher(email);
        if(matcher.find()){
            UIManager.getInstance().addUser(email,password);
        }
    }
}
