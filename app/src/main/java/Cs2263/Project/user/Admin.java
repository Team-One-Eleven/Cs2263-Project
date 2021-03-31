/**
 * Admin class
 *
 * The regular user class. In addition to the UserArchetype's properties
 * it has a list of user info and other properties supporting that.
 *
 * /TODO solve THE ADMIN PROBLEM
 *
 * @author  Traae
 * @version 0.5
 * @since 3/25/2021
 */

package Cs2263.Project.user;

import Cs2263.Project.listable.UserCredentials;

import java.io.Serializable;
import java.util.LinkedList;

public class Admin extends UserArchetype implements Serializable {

    // Variables
    private LinkedList<UserCredentials> userCredentialsList;

    // Constructor
    public Admin() {
        userCredentialsList = new LinkedList<>();
    }

    // Methods
    public LinkedList<UserCredentials> getUserCredentialsList() {
        return userCredentialsList;
    }
}
