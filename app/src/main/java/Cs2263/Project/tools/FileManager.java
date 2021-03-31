/**
 * File Manager class
 *
 * Contains all the functions needed for loading and saving our json files
 *
 * STILL DEVELOPING
 *
 * @author  Traae
 * @version 0.0
 * @since 3/25/2021
 */

package Cs2263.Project.tools;

import Cs2263.Project.Orchestrator;
import Cs2263.Project.listable.UserInfo;
import Cs2263.Project.user.User;

import java.util.LinkedList;

public class FileManager {
    //user information should be stored in a file called "./config/users.json"
    //System settings should be stored in "./config/config.json"

    //base directory name for user information (i.e., "./data")
    //relative to the base program's directory or possibly stored in the user's home directory

    //User data should be saved in a folder called "./data" in a file called "<user_id>.json" where `<user_id>` is the user's identifier

    private static final String SYSTEM_CONFIG_FILE = "./config/config.json";
    private static final String USER_LIST_DATA_FILE = "./config/users.json";


    private Orchestrator orchestrator;

    // Constructor
    public FileManager(Orchestrator o){
        this.orchestrator = o;
    }

    public LinkedList<UserInfo> loadUserList(String userListFile){
        //TODO load the user list file
    }
    public void saveUserList(LinkedList<UserInfo> userList){
        //TODO save it
    }
    public User loadUser(String userFile){
        // TODO laod it
    }
    public void saveUser(User u){
        //TODO savie it
    }
    public void deletePicture(String pictureFile){

    }
    public void newPicture(String pictureFile){

    }
    //public LoadPicture(String pictureFile) : PictureSpriteThing-JavaFX

    public void loadSettings(){}
    public void saveSettings(){}



}
