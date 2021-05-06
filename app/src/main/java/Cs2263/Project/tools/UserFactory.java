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
import Cs2263.Project.listable.lists.ToDoList;

public class UserFactory {


    private Orchestrator orchestrator;

    // Constructor
    public UserFactory(Orchestrator o){
        this.orchestrator = o;
    }

    // Methods
    // Makes
    public User makeUser(){
        User u = new User();
        ToDoList mainList = orchestrator.getItemFactory().makeToDOList();
        mainList.setTitle("Main");
        mainList.setDescription("a starting list.");
        u.setMainList(mainList);
        return u;
    }
    public UserCredentials makeUserInfo(String email, String password, double userId) {
        UserCredentials newInfo = new UserCredentials();
        newInfo.setUserEmail(email);
        newInfo.setUserPassword(password);
        newInfo.setUserId(userId);
        newInfo.setUserFile("/"+ userId + Configuration.USER_DATA_FILE_SUFFIX);
        return newInfo;
    }



}
