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
    private String userId;
    private String userFile;

    // Constructor
    public UserCredentials(){
        userEmail = "";
        userPassword = "";
        userId = "";
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
    public String getUserId() {
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
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setUserFile(String userFile) {
        this.userFile = userFile;
    }
    // ListableItem Interface
    @Override
    public String getTitle() {
        return userEmail;
    }
    @Override
    public ListableType getType(){
        return type;
    }
}
