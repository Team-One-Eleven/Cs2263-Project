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
import Cs2263.Project.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class FileManager {


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
        String readIn;

        try {
            readIn = Files.readString(Paths.get(USER_LIST_DATA_FILE));
        }
        catch (IOException e){
            orchestrator.makeDefaultAdmin();
            readIn = Files.readString(Paths.get(USER_LIST_DATA_FILE));
        }
        Type type = new TypeToken<LinkedList<UserCredentials>>(){}.getType();
        return gson.fromJson(readIn, type);
    }
    public void saveUserList() throws IOException {
       String writeOut = gson.toJson(orchestrator.getUserList());

        Files.writeString(Paths.get(USER_LIST_DATA_FILE), writeOut);
    }
    public User loadUser(String filePath) throws IOException {
        String readIn = Files.readString(Paths.get(filePath));
        Type type = new TypeToken<User>(){}.getType();
        return gson.fromJson(readIn, type);
    }
    public void saveUser(User toSave, UserCredentials toSaveInfo) throws IOException {
        String writeOut = gson.toJson(toSave);
        Files.writeString(Paths.get(toSaveInfo.getUserFile()), writeOut);
    }
    public void deletePicture(String pictureFile){

    }
    public void newPicture(String pictureFile){

    }
    //public LoadPicture(String pictureFile) : PictureSpriteThing-JavaFX

    public Configuration loadConfig() throws IOException {
        Configuration c;
        try {
            String readIn = Files.readString(Paths.get(SYSTEM_CONFIG_FILE));
            Type type = new TypeToken<LinkedList<UserCredentials>>(){}.getType();
            c = gson.fromJson(readIn, type);
        }
        catch (IOException e){
            c = new Configuration();
            c.recoverUserIDseed(orchestrator.getUserList());
        }
        return c;

    }
    public void saveConfiguration() throws IOException {
        String writeOut = gson.toJson(orchestrator.getConfig());
        Files.writeString(Paths.get(SYSTEM_CONFIG_FILE), writeOut);
    }



}
