/**
 * Orchestrator Test Class
 *
 *  The Orchestrator is the center peice of the systems tools.
 *  It's method's (that aren't getters and setters for the tools and data)
 *  are arrangements of other methods from throughout the system.
 *
 *  Making sure that the systems tools are used when needed and in order.
 *
 * @author  Traae
 * @version 1.0
 * @since 4/30/2021
 */

package Cs2263.Project;

import Cs2263.Project.listable.UserCredentials;
import org.junit.jupiter.api.Test;
import javax.security.auth.login.FailedLoginException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrchestratorTest {

    @Test void getInstanceTEST() {
        Orchestrator o = Orchestrator.getInstance();
        assertTrue(o != null);
    }

    @Test void registerUserTEST() {
        /**
         * Register user calls other important method that need testing.
         *
         * This test only checks to see if the the new info made it into the list.
         *
         * The actual existence of the saved dat will be tested in the file mangaer
         */

        Orchestrator o = Orchestrator.getInstance();

        String emailTEST = "test@gmail.com";
        String passwordTEST = "testPassword";

        assertTrue(o.registerUser(emailTEST, passwordTEST));

        boolean found = false;
        for (UserCredentials u : o.getUserList()){
            if ((u.getUserEmail() == emailTEST) & (u.getUserPassword() == passwordTEST)){
                found = true;
            }
        }
        assertTrue(found);
    }


    @Test void loginUserTEST()throws FailedLoginException {
        /**
         * This test for a confirmed login.
         * however, in our simplification of the system,
         * I removed any shared data between the user Credentials and the user file.
         *
         * This means that I currently have no way of knowing it the correct
         * user was loaded.
         *
         * TODO fix the aforementioned problem
         */
        Orchestrator o = Orchestrator.getInstance();

        if (!o.getUserList().isEmpty()){
            UserCredentials u = o.getUserList().get(0);
            o.loginUser(u.getUserEmail(), u.getUserPassword());
            assertTrue(o.getActiveInfo() == u);
            assertTrue(o.getActiveUser() != null);
        }
    }


    @Test public void logoutUserTEST() {
        /**
         * This is another composite method containing other
         * important method in need of testing.
         *
         * This will only test the end result of the active
         * user, info, and list have been cleard.
         */

        Orchestrator o = Orchestrator.getInstance();

        o.logoutUser();
        assertTrue(o.getActiveUser() == null);
        assertTrue(o.getActiveInfo() == null);
        assertTrue(o.getMasterList() == null);

    }


}
