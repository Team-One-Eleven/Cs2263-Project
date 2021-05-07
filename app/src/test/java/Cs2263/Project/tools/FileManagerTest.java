/**
 * File Manager Tests
 *
 * Note: Based on what we wre taught, I think my test code is bad.
 * Many tests are complicated, and require set up. Sed set up is done manually
 * and doesn't use the @before features at this time.
 *
 *
 * @author  Traae
 * @version 1.0
 * @since 4/30/2021
 */

package Cs2263.Project.tools;

import Cs2263.Project.*;
import Cs2263.Project.listable.UserCredentials;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.LoadException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {


    @Test
    public void loadUserListTEST() throws IOException {
        FileManager fileman = new FileManager(Orchestrator.getInstance());
        ArrayList<UserCredentials> testUserList;
        testUserList = fileman.loadUserList();
        assertNotNull(testUserList);
    }

    @Test public void saveUserListTEST() {
        Orchestrator o = Orchestrator.getInstance();
        FileManager fileman = new FileManager(o);
        ArrayList<UserCredentials> testCopyList = o.getUserList();

        fileman.saveUserList();
        fileman.loadUserList();
        assertTrue(testCopyList.size() == o.getUserList().size());

        for (int i=0; i<testCopyList.size(); i++){
            assertTrue(testCopyList.get(i).getUserId() == o.getUserList().get(i).getUserId());
        }

    }



    @Test public void loadUserTEST() {
        Orchestrator o = Orchestrator.getInstance();
        FileManager fileman = new FileManager(o);
        User
        User testUser = fileman.loadUser(o.getUserList().get(0).getUserFile());

        assertTrue(testUser != null);

    }

    @Test public void saveUserTEST() {
        Orchestrator o = Orchestrator.getInstance();
        FileManager fileman = new FileManager(o);

        UserCredentials testInfo = o.getUserList().get(0);
        User testUser = fileman.loadUser(testInfo.getUserFile());
        String testBio = "Test Biography";
        testUser.setBiography(testBio);
        fileman.saveUser(testUser, testInfo);

        testUser = fileman.loadUser(testInfo.getUserFile());

        assertTrue(testUser.getBiography() == testBio);


    }

    @Test public void loadConfigTEST() {
        Orchestrator o = Orchestrator.getInstance();
        FileManager fileman = new FileManager(o);

        Configuration c = fileman.loadConfig();

        assertTrue(c != null);

    }
    @Test public void saveConfigurationTEST()  {
        Orchestrator o = Orchestrator.getInstance();
        FileManager fileman = new FileManager(o);

        Configuration c = fileman.loadConfig();

        double tester = c.getNextUserIDseed() + 1;

        fileman.saveConfiguration(c);

        c = fileman.loadConfig();

        assertTrue(tester == c.getNextUserIDseed());
    }



}
