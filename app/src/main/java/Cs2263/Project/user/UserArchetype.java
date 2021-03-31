/**
 * User Archetype abstract class
 *
 * Provides the layout for both standard users and the Admin
 *
 * @author  Traae
 * @version 1.0
 * @since 3/25/2021
 */

package Cs2263.Project.user;

public abstract class UserArchetype {
    //Variables
    private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private String pictureFile;
    private String biography;
    private String password;

    // Constructor
    public UserArchetype(){
        userID = "";
        firstName = "";
        lastName = "";
        email = "";
        pictureFile = "";
        biography = "";
        password = "";
    }

    // Methods
    // GETTERS
    public String getUserID() {
        return userID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPictureFile() {
        return pictureFile;
    }
    public String getBiography() {
        return biography;
    }
    public String getPassword() {
        return password;
    }
    // SETTERS
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPictureFile(String pictureFile) {
        this.pictureFile = pictureFile;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
}
