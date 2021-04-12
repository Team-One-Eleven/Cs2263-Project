/**
 * User info
 *
 * the info that will be stored for data in the list of users.
 *
 * @author  Traae
 * @version 1.0
 * @since 3/31/2021
 */

package Cs2263.Project.listable;

import java.io.Serializable;

public class UserCredentials implements Serializable, ListableItem {
    // Variables
    // Listable type
    private static final ListableType type = ListableType.UserInfo;
    // Instance variables
    private String userEmail;
    private String userPassword;
    private double userId;
    private String userFile;

    // Constructor
    public UserCredentials(){
        userEmail = "";
        userPassword = "";
        userId = 1;
        userFile = "";
    }
    // Methods
    // GETTERS AND SETTERS
    public String getUserEmail() {
        return userEmail;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public double getUserId() {
        return userId;
    }
    public String getUserFile() {
        return userFile;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public void setUserId(double userId) {
        this.userId = userId;
    }
    public void setUserFile(String userFile) {
        this.userFile = userFile;
    }

    @Override
    public String getTitle() {
        return getUserEmail();
    }
    @Override
    public ListableType getType() {
        return type;
    }

    @Override
    public String toString(){
        return getTitle();
    }
}
