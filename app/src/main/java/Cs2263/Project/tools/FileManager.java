/**
 * File Manager class
 *
 * Contains all the functions needed for loading and saving our json files
 *
 * TILL DEVELOPINGS
 * Everything but the picture
 *
 * @author  Traae
 * @version .9
 * @since 3/31/2021
 */

package Cs2263.Project.tools;

import Cs2263.Project.Configuration;
import Cs2263.Project.Orchestrator;
import Cs2263.Project.listable.UserCredentials;
import Cs2263.Project.user.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.ObjectInputFilter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileManager {
    //user information should be stored in a file called "./config/users.json"
    //System settings should be stored in "./config/config.json"

    //base directory name for user information (i.e., "./data")
    //relative to the base program's directory or possibly stored in the user's home directory

    //User data should be saved in a folder called "./data" in a file called "<user_id>.json" where `<user_id>` is the user's identifier

    private static final String SYSTEM_CONFIG_FILE = "./config/config.json";
    private static final String USER_LIST_DATA_FILE = "./config/users.json";

    private Gson gson;


    private Orchestrator orchestrator;

    // Constructor
    public FileManager(Orchestrator o){
        this.orchestrator = o;
        gson = new Gson();
    }

    public LinkedList<UserCredentials> loadUserList() throws IOException {
        String readIn = Files.readString(Paths.get(USER_LIST_DATA_FILE));

        Type type = new TypeToken<LinkedList<UserCredentials>>(){}.getType();

        return gson.fromJson(readIn, type);
    }
    public void saveUserList() throws IOException {
       String writeOut = gson.toJson(orchestrator.getUserList());

        Files.writeString(Paths.get(USER_LIST_DATA_FILE), writeOut);
    }
    public User loadUser() throws IOException {
        String readIn = Files.readString(Paths.get(orchestrator.getActiveInfo().getUserFile()));
        Type type = new TypeToken<User>(){}.getType();
        return gson.fromJson(readIn, type);
    }
    public void saveUser() throws IOException {
        String writeOut = gson.toJson(orchestrator.getActiveUser());
        Files.writeString(Paths.get(orchestrator.getActiveInfo().getUserFile()), writeOut);
    }
    public void deletePicture(String pictureFile){

    }
    public void newPicture(String pictureFile){

    }
    //public LoadPicture(String pictureFile) : PictureSpriteThing-JavaFX

    public Configuration loadConfig() throws IOException {
        String readIn = Files.readString(Paths.get(SYSTEM_CONFIG_FILE));
        Type type = new TypeToken<LinkedList<UserCredentials>>(){}.getType();
        return gson.fromJson(readIn, type);
    }
    public void saveConfiguration() throws IOException {
        String writeOut = gson.toJson(orchestrator.getConfig());
        Files.writeString(Paths.get(SYSTEM_CONFIG_FILE), writeOut);
    }



}
