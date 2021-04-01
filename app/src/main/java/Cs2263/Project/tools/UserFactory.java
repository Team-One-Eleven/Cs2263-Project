/**
 * User Factory class
 *
 * Produces a new userInfo, called from orchestrator.registerUser()
 *
 * Produces a new User using userInfo.
 *
 * TODO need to solve THE ADMIN PROBLEM
 *
 * @author  Traae
 * @version 0.9
 * @since 3/31/2021
 */

package Cs2263.Project.tools;

import Cs2263.Project.Orchestrator;
import Cs2263.Project.user.UserCredentials;
import Cs2263.Project.user.User;


public class UserFactory {
    public static final String USER_DATA_BASE = "./data/";
    public static final String USER_DATA_FILE_SUFFIX = ".json";

    private Orchestrator orchestrator;

    // Constructor
    public UserFactory(Orchestrator o){
        this.orchestrator = o;
    }

    // Methods
    // Makes
    public User makeUser(){
        User u = new User();
        return new User();
    }
    public UserCredentials makeUserInfo(String email, String password, String userId) {
        UserCredentials newInfo = new UserCredentials();
        newInfo.setUserEmail(email);
        newInfo.setUserPassword(password);
        newInfo.setUserId(userId);
        newInfo.setUserFile(USER_DATA_BASE + userId + USER_DATA_FILE_SUFFIX);
        return newInfo;
    }



}
