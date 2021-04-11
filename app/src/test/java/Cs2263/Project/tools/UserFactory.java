/**
 * User Factory class
 *
 * Produces a new userInfo, called from orchestrator.registerUser()
 *
 * Produces a new User using userInfo.
 *
 *
 * @author  Traae
 * @version 1.0
 * @since 3/31/2021
 */

package Cs2263.Project.tools;

import Cs2263.Project.ConfigurationTest;
import Cs2263.Project.OrchestratorTest;
import Cs2263.Project.listable.UserCredentials;


public class UserFactory {


    private OrchestratorTest orchestrator;

    // Constructor
    public UserFactory(OrchestratorTest o){
        this.orchestrator = o;
    }

    // Methods
    // Makes
    public User makeUser(){
        User u = new User();
        return new User();
    }
    public UserCredentials makeUserInfo(String email, String password, double userId) {
        UserCredentials newInfo = new UserCredentials();
        newInfo.setUserEmail(email);
        newInfo.setUserPassword(password);
        newInfo.setUserId(userId);
        newInfo.setUserFile(ConfigurationTest.USER_DATA_BASE + userId + ConfigurationTest.USER_DATA_FILE_SUFFIX);
        return newInfo;
    }



}
