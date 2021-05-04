/**
 * Configuration Test Class.
 *
 * Since the configuration got boild down to just the Id seed,
 * that's all we have to test.
 *
 * However, the tests kinda have to replicate the function inorder to test them.
 *
 *
 * @author Traae
 * @version 1.0
 * @since 4/30/2021
 */

package Cs2263.Project;

import Cs2263.Project.listable.UserCredentials;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfigurationTest {


    @Test void TESTgetNextUserIDseed() {
        Configuration c = new Configuration();
        assertTrue(c.getNextUserIDseed() == 2);
        for (int i=3; i<10; i++){
            assertTrue(c.getNextUserIDseed() == i);
        }
    }


    @Test void TESTrecoverUserIDseed(){
        Orchestrator o = Orchestrator.getInstance();

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
