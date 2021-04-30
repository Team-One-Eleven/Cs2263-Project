/**
 * User Factory Test class
 *
 * The user factory just creates a user, which only really stores data.
 * The tests are really just testing to see if something appeared.
 *
 * @author  Traae
 * @version 1.0
 * @since 4/30/2021
 */

package Cs2263.Project.tools;

import Cs2263.Project.ConfigurationTest;
import Cs2263.Project.Orchestrator;
import Cs2263.Project.OrchestratorTest;
import Cs2263.Project.User;
import Cs2263.Project.listable.UserCredentials;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserFactoryTest {


    @Test
    public void makeUserTEST(){
        /**
         * Make user just returns a blank user,
         * so checking the values is really testing the User constructor,
         * but I did it anyways.
         */

        Orchestrator o = Orchestrator.getInstance();
        UserFactory factory = new UserFactory(o);

        User testUser = factory.makeUser();

        assertTrue(testUser != null);
        assertTrue(testUser.getFirstName() == "");
        assertTrue(testUser.getLastName() == "");
        assertTrue(testUser.getBiography() == "");

    }



    @Test public void makeUserInfoTEST() {
        Orchestrator o = Orchestrator.getInstance();
        UserFactory factory = new UserFactory(o);

        String testEmail = "test@test.com";
        String testPassword = "password123";
        Double testID = 123.0;

        UserCredentials newInfo = factory.makeUserInfo(testEmail, testPassword , testID);

        assertTrue(newInfo != null);
        assertTrue(newInfo.getUserEmail() == testEmail);
        assertTrue(newInfo.getUserPassword() == testPassword);
        assertTrue(newInfo.getUserId() == testID);

    }



}
