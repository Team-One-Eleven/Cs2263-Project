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
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {
    // Variables
    private Orchestrator orchestrator;
    private Gson gson;

    // Constructor
    public FileManager(Orchestrator o){
        this.orchestrator = o;
        gson = new GsonBuilder().setPrettyPrinting().create();
    }


    public ArrayList<UserCredentials> loadUserList() {
        /**
         * This function loads the user list
         *
         * If it fails to do so, it will call the the orchestrator to create default data, and then
         * try to read that in instead.
         */
        String readIn;
        ArrayList<UserCredentials> theList;
        try {
            readIn = Files.readString(Paths.get(userListPath()));
            Type type = new TypeToken<ArrayList<UserCredentials>>(){}.getType();
            theList = gson.fromJson(readIn, type);
        }
        catch (IOException e){
            theList = orchestrator.makeDefaultUserList();
            System.out.println("Load User List Exception");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
//            try{
//                //save the new list and reload
//                readIn = Files.readString(Paths.get(userListPath()));
//            }catch (Exception x){
//                System.out.println("\n Tried to make default, then: \n");
//                System.out.println("Second Exception");
//                System.out.println(e.toString());
//                System.out.println(e.getMessage());
//            }
        }

        return theList;
    }


    public void saveUserList() {
        /**
         * Saves the user list.
         */

        try {
            String writeOut = gson.toJson(orchestrator.getUserList());
            Files.writeString(Paths.get(userListPath()), writeOut);
        }
        catch (Exception e){
            System.out.println("Save User List Exception");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }

    }


    public User loadUser(String filePath) {
        /**
         * Loads user
         */
        User theUser;
        try {
            String readIn = Files.readString(Paths.get(userPath(filePath)));
            Type type = new TypeToken<User>(){}.getType();
            theUser = gson.fromJson(readIn, type);
        }
        catch (Exception e){
            theUser = orchestrator.makeDefaultUser();
            System.out.println("load user Exception");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
        return theUser;
    }

    public void saveUser(User toSave, UserCredentials toSaveInfo)  {
        /**
         * Saves user
         */
        try{
            String writeOut = gson.toJson(toSave);
            Files.writeString(Paths.get(userPath(toSaveInfo.getUserFile())), writeOut);
        }
        catch (Exception e){
            System.out.println("Save User Exception");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }


    }

    public Configuration loadConfig() {
        /**
         * This method loads the configuration file.
         *
         * If it fails, it will create a new configuration and attempt to recover the
         * the user id seed.
         */
        Configuration c;
        try {
            String readIn = Files.readString(Paths.get(configFilePath()));
            Type type = new TypeToken<ArrayList<UserCredentials>>(){}.getType();
            c = gson.fromJson(readIn, type);
        }
        catch (Exception e){
            c = new Configuration();
            c.recoverUserIDseed(orchestrator.getUserList());
            System.out.println("Load config Exception");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
        return c;

    }
    public void saveConfiguration() {
        /**
         * Saves config.
         */
        try {
            String writeOut = gson.toJson(orchestrator.getConfig());
            Files.writeString(Paths.get(configFilePath()), writeOut);
        }
        catch (Exception e){
            System.out.println("save config Exception");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }

    }


    /**
     * PATH METHODS
     *
     * The following is a set of private methods that add the base directory to a file path
     * and return the new combined string.
     *
     */
    private String configFilePath(){
        return Configuration.BASE_DIRECTORY + Configuration.SYSTEM_CONFIG_FILE ;
    }
    private String userListPath(){
        return Configuration.BASE_DIRECTORY + Configuration.USER_LIST_DATA_FILE;
    }
    private String userPath(String filePath){
        return Configuration.BASE_DIRECTORY + filePath;
    }


}
