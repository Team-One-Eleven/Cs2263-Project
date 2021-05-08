/**
 * Add user first and last name. Ideally called on first login or when the user wants to change their settings.
 *
 * @Author Braydon Spaulding
 */

package Cs2263.UI.Commands;

import Cs2263.Project.User;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddFirstLastNameCommand implements UICommand{

    String fName;
    String lName;

    //Pattern for ensuring alphabetic characters only in name
    public static final Pattern VALID_NAME_PATTERN =
            Pattern.compile("^[A-Z]{0,20}$", Pattern.CASE_INSENSITIVE);

    /**
     *
     * @param f  User first name
     * @param l  User last name
     * @throws IllegalArgumentException if name is too long or contains non-alphabetic characters.
     */

    public AddFirstLastNameCommand(String f, String l) throws IllegalArgumentException{

        Matcher fNameMatcher = VALID_NAME_PATTERN.matcher(f);
        Matcher lNameMatcher = VALID_NAME_PATTERN.matcher(l);

        if(fNameMatcher.matches() || lNameMatcher.matches()){
            this.fName = f;
            this.lName = l;
        }
        else{
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void execute() {
        User activeUser = orchestrator.getActiveUser();
        activeUser.setFirstName(fName);
        activeUser.setLastName(lName);
    }
}