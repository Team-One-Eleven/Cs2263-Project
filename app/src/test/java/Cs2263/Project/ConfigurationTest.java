/**
 * Configurations for the system.
 *
 * Currently the config has had its functionality reduced from the project's original scale.
 *
 * It's only being used for the user id seed, which is super important,
 * and for holding global variables used through out the orchestrator and associated tools.
 *
 * @author Traae
 * @version 1.0
 * @since 4/6/2021
 */

package Cs2263.Project;

import Cs2263.Project.listable.UserCredentials;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfigurationTest {


    @Test void TESTgetNextUserIDseed() {
        Configuration c = new Configuration();
        assertTrue(c.getNextUserIDseed() == 2);
        for (int i=3; i<100; i++){
            assertTrue(c.getNextUserIDseed() == i);
        }
    }


    @Test void TESTrecoverUserIDseed() throws IOException {
        OrchestratorTest o = OrchestratorTest.getInstance();

        Configuration c = new Configuration();

        c.recoverUserIDseed(o.getUserList());

        double highest = 0;
        for (UserCredentials info : o.getUserList()){
            double id = info.getUserId();
            if (id > highest){
                highest = id;
            }
        }

        assertTrue(c.getNextUserIDseed()>highest);
    }
}
