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
import java.io.Serializable;
import java.util.ArrayList;

public class Configuration implements Serializable {
    // GLOBALS FOR THE SYSTEM
    // File paths
    public static final String BASE_DIRECTORY = "./TODOsystem";
    public static final String USER_DATA_DIR = "/data";
    public static final String CONFIG_DIR = "/config";
    public static final String SYSTEM_CONFIG_FILE = "/config.json";
    public static final String USER_LIST_DATA_FILE = "/users.json";

    public static final String USER_DATA_FILE_SUFFIX = ".json";
    // Default Admin Credentials
    public static final String ADMIN_EMAIL_DEFAULT = "Admin@todo.system";
    public static final String ADMIN_PASSWORD_DEFAULT = "123password";
    public static final Double ADMIN_ID_DEFAULT = 0.0;

    // Variables
    private int userIDseed;

    // Constructor
    public Configuration(){
        userIDseed = 1;
    }

    // Methods
    public int getNextUserIDseed() {
        /**
         * This method increments the userIDseed and then returns the new one.
         *
         * the seed is only ever incremented up, thus ensuring the id's a re unique
         */
        userIDseed++;
        return userIDseed;
    }
    public void recoverUserIDseed(ArrayList<UserCredentials> userList){
        /**
         * This function scans the userList for the highest id then sets the user Id see to a number
         * above that.
         *
         * Available in the event that data is deleted or corrupted.
         *
         * Aside from this, there is no other way to fix the seed.
         */
        double highest = 0;
        for (UserCredentials info : userList){
            double id = info.getUserId();
            if (id > highest){
                highest = id;
            }
        }
    }
}
