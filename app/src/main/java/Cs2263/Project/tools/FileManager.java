/**
 * File Manager class
 *
 * Contains all the functions needed for loading and saving our json files
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {
    // Variables
    private Orchestrator Orchestrator;
    private Gson gson;

    // Constructor
    public FileManager(Orchestrator o){
        this.Orchestrator = o;
        gson = new Gson();
    }


    public ArrayList<UserCredentials> loadUserList() throws IOException {
        /**
         * This function loads the user list
         *
         * If it fails to do so, it will call the the orchestrator to create default data, and then
         * try to read that in instead.
         */
        String readIn;
        try {
            readIn = Files.readString(Paths.get(Configuration.BASE_DIRECTORY + Configuration.USER_LIST_DATA_FILE));
        }
        catch (IOException e){
            Orchestrator.makeDefaultUserList();
            readIn = Files.readString(Paths.get(Configuration.BASE_DIRECTORY + Configuration.USER_LIST_DATA_FILE));
        }
        Type type = new TypeToken<ArrayList<UserCredentials>>(){}.getType();
        return gson.fromJson(readIn, type);
    }

    public void saveUserList() throws IOException {
        /**
         * Saves the user list.
         */
       String writeOut = gson.toJson(Orchestrator.getUserList());
        Files.writeString(Paths.get(Configuration.BASE_DIRECTORY + Configuration.USER_LIST_DATA_FILE), writeOut);
    }
    public User loadUser(String filePath) throws IOException {
        /**
         * Loads user
         */
        String readIn = Files.readString(Paths.get(Configuration.BASE_DIRECTORY + filePath));
        Type type = new TypeToken<User>(){}.getType();
        return gson.fromJson(readIn, type);
    }
    public void saveUser(User toSave, UserCredentials toSaveInfo) throws IOException {
        /**
         * Saves user
         */
        String writeOut = gson.toJson(toSave);
        Files.writeString(Paths.get(Configuration.BASE_DIRECTORY + toSaveInfo.getUserFile()), writeOut);
    }


    public Configuration loadConfig() throws IOException {
        /**
         * This method loads the configuration file.
         *
         * If it fails, it will create a new configuration and attempt to recover the
         * the user id seed.
         */
        Configuration c;
        try {
            String readIn = Files.readString(Paths.get(Configuration.SYSTEM_CONFIG_FILE));
            Type type = new TypeToken<ArrayList<UserCredentials>>(){}.getType();
            c = gson.fromJson(readIn, type);
        }
        catch (IOException e){
            c = new Configuration();
            c.recoverUserIDseed(Orchestrator.getUserList());
        }
        return c;

    }
    public void saveConfiguration() throws IOException {
        /**
         * Saves config.
         */
        String writeOut = gson.toJson(Orchestrator.getConfig());
        Files.writeString(Paths.get(Configuration.SYSTEM_CONFIG_FILE), writeOut);
    }



}
