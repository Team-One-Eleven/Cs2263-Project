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
    private String firstName;
    private String lastName;
    private String pictureFile;
    private String biography;


    // Constructor
    public UserArchetype(){
        firstName = "";
        lastName = "";
        pictureFile = "";
        biography = "";

    }

    // Methods
    // GETTERS

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPictureFile() {
        return pictureFile;
    }
    public String getBiography() {
        return biography;
    }

    // SETTERS
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPictureFile(String pictureFile) {
        this.pictureFile = pictureFile;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
}
