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

import Cs2263.Project.Configuration;
import Cs2263.Project.Orchestrator;
import Cs2263.Project.listable.UserCredentials;
import Cs2263.Project.User;

public class UserFactory {


    private Orchestrator Orchestrator;

    // Constructor
    public UserFactory(Orchestrator o){
        this.Orchestrator = o;
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
        newInfo.setUserFile(Configuration.USER_DATA_BASE + userId + Configuration.USER_DATA_FILE_SUFFIX);
        return newInfo;
    }



}
