/**
 * Register user command object. Checks for valid email format and adds user.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Commands;
import Cs2263.Project.User;
import java.io.IOException;
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

    /**
     * Creates register user command with email and password, which sets the email and password fields of the object
     * to the arguments if they are of valid form. See below.
     *
     * @param e  User email (user@email.com)
     * @param p  User password (alphanumeric and under 20 characters)
     * @throws IllegalArgumentException if arguments don't match requirements above
     */


    public RegisterUserCommand(String e, String p) throws IllegalArgumentException{

        Matcher emailMatcher = VALID_EMAIL_PATTERN.matcher(e);
        Matcher passwordMatcher = VALID_PASSWORD_PATTERN.matcher(p);
        if(emailMatcher.find() && passwordMatcher.find()){
            this.email = e;
            this.password = p;
       }
        else{
            throw new IllegalArgumentException();
        }

    }

    /**
     * Calls orchestrator to register user with values.
     */

    @Override
    public void execute() {
        try {
            if (orchestrator.registerUser(email, password)) {
                User newUser = orchestrator.getActiveUser();
                newUser.setFirstName(fName);
                newUser.setLastName(lName);
            }
        }
        catch (IOException e) {
            System.out.println("IOException in RegisterUserCommand execution");
        }

    }
}
