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

import Cs2263.Project.*;
import Cs2263.Project.listable.UserCredentials;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.LoadException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {


    @Test
    void TEST_loadUserList_DATA_AVAILIBLE() throws IOException {
        FileManager fileman = new FileManager(Orchestrator.getInstance());
        ArrayList<UserCredentials> testUserList;
            testUserList = fileman.loadUserList();
            // for testing purposes, I need to manually remove the example 1  user to show that it isn't the example list data
            for (UserCredentials u : testUserList){
                assertFalse(u.getUserEmail() == "example1@example.com");
            }
    }

    @Test
    void TEST_loadUserList_DATA_UNAVAILIBLE() throws IOException {
        FileManager fileman = new FileManager(Orchestrator.getInstance());
        ArrayList<UserCredentials> testUserList;

            testUserList = fileman.loadUserList();
            boolean adminpresent = false;
            boolean ex1present = false;
            boolean ex2present = false;
            boolean ex3present = false;
            for (UserCredentials u : testUserList){
                if (u.getUserEmail() == Configuration.ADMIN_EMAIL_DEFAULT) { adminpresent = true; }
                if (u.getUserEmail() == "example1@example.com") { ex1present = true; }
                if (u.getUserEmail() == "example2@example.com") { ex2present = true; }
                if (u.getUserEmail() == "example3@example.com") { ex3present = true; }
            }
            assertTrue(testUserList.size() == 4);
            assertTrue(adminpresent);
            assertTrue(ex1present);
            assertTrue(ex2present);
            assertTrue(ex3present);

    }

//    public ArrayList<UserCredentials> loadUserList() throws IOException {
//        /**
//         * This function loads the user list
//         *
//         * If it fails to do so, it will call the the orchestrator to create default data, and then
//         * try to read that in instead.
//         */
//        String readIn;
//        try {
//            readIn = Files.readString(Paths.get(ConfigurationTest.USER_LIST_DATA_FILE));
//        }
//        catch (IOException e){
//            orchestrator.makeDefaultUserList();
//            readIn = Files.readString(Paths.get(ConfigurationTest.USER_LIST_DATA_FILE));
//        }
//        Type type = new TypeToken<ArrayList<UserCredentials>>(){}.getType();
//        return gson.fromJson(readIn, type);
//    }
//
//    public void saveUserList() throws IOException {
//        /**
//         * Saves the user list.
//         */
//       String writeOut = gson.toJson(orchestrator.getUserList());
//        Files.writeString(Paths.get(ConfigurationTest.USER_LIST_DATA_FILE), writeOut);
//    }
//    public User loadUser(String filePath) throws IOException {
//        /**
//         * Loads user
//         */
//        String readIn = Files.readString(Paths.get(filePath));
//        Type type = new TypeToken<User>(){}.getType();
//        return gson.fromJson(readIn, type);
//    }
//    public void saveUser(User toSave, UserCredentials toSaveInfo) throws IOException {
//        /**
//         * Saves user
//         */
//        String writeOut = gson.toJson(toSave);
//        Files.writeString(Paths.get(toSaveInfo.getUserFile()), writeOut);
//    }
//
//    public ConfigurationTest loadConfig() throws IOException {
//        /**
//         * This method loads the configuration file.
//         *
//         * If it fails, it will create a new configuration and attempt to recover the
//         * the user id seed.
//         */
//        ConfigurationTest c;
//        try {
//            String readIn = Files.readString(Paths.get(ConfigurationTest.SYSTEM_CONFIG_FILE));
//            Type type = new TypeToken<ArrayList<UserCredentials>>(){}.getType();
//            c = gson.fromJson(readIn, type);
//        }
//        catch (IOException e){
//            c = new ConfigurationTest();
//            c.recoverUserIDseed(orchestrator.getUserList());
//        }
//        return c;
//
//    }
//    public void saveConfiguration() throws IOException {
//        /**
//         * Saves config.
//         */
//        String writeOut = gson.toJson(orchestrator.getConfig());
//        Files.writeString(Paths.get(ConfigurationTest.SYSTEM_CONFIG_FILE), writeOut);
//    }



}
