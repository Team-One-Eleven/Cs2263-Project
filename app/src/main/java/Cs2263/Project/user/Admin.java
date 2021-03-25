/**
 * Admin class
 *
 * The regular user class. In addition to the UserArchetype's properties
 * it has a list of user info and other properties supporting that.
 *
 * STILL DEVELOPING
 *
 * A question that needs to be resolved is ensuring that an Admin always exists.
 * I may need to hard code some default admin data that will create a new Admin
 * if an admin doesn't already exist
 *
 * @author  Traae
 * @version 0.5
 * @since 3/25/2021
 */

package Cs2263.Project.user;

import Cs2263.Project.listable.UserInfo;

import java.io.Serializable;
import java.util.LinkedList;

public class Admin extends UserArchetype implements Serializable {

    // Variables
    private LinkedList<UserInfo> userInfoList;

    // Constructor
    public Admin(String id, String first, String last, String email, String password) {
        super(id, first, last, email, password);
    }

    // Methods
    public LinkedList<UserInfo> getUserInfoList() {
        return userInfoList;
    }
}
