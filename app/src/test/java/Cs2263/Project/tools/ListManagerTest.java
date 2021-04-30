/**
 *
 * List Manager Test
 *
 * NOTE: The test for the list manager is 1 method.
 *
 * There are only 2 functions, Construct & Destruct,
 * but in oder to test them I have to call the other.
 *
 * Since being able to test either function requires the
 * the other to function, they've being rolled into 1 test.
 *
 * This is, of course, on top of my already complicated test code.
 *
 * The original system wasn't written to be tested. I'll learn from this.
 * @author  Traae
 * @version 1.0
 * @since 4/30/2021
 */

package Cs2263.Project.tools;

import Cs2263.Project.Orchestrator;
import Cs2263.Project.User;
import Cs2263.Project.listable.UserCredentials;
import Cs2263.Project.listable.tasks.ParentTask;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.FailedLoginException;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ListManagerTest {

    private Orchestrator o;
    boolean isInit = false;


    private void initTestResources() {
        o = Orchestrator.getInstance();

        String testEmail = "LMtest@test.com";
        String testPassword = "LMpassword";
        String testName = "Manage";

        if (!isInit) {
            o.registerUser(testEmail, testPassword);
            try {
                o.loginUser(testEmail, testPassword);
                o.getActiveUser().setFirstName(testName);
                o.getActiveUser().setLastName(testName);

                o.getActiveUser().getTheLists().add(o.makeAnExampleListStructure(1));
                o.getActiveUser().getTheLists().add(o.makeAnExampleListStructure(2));
                o.getActiveUser().getTheLists().add(o.makeAnExampleListStructure(3));

            } catch (FailedLoginException e) {
                System.out.println("LIST MANAGER TEST: init test resources for the tests \n" +
                        " couldn't register the test user");
            }
        }
    }



    @Test
    public void masterListTEST(){
        /**
         * This test will double check the count of the tasks of our user,
         * And it will count the number of sections those tasks belong to.
         *
         * Then we'll compare that with our loged in user.
         */

        initTestResources();
        int taskCount;
        int taskAssignedSectionCount = 0;

        FileManager fileman = new FileManager(o);
        ListManager listMan = new ListManager(o);

        UserCredentials info = o.getActiveInfo();
        User testCopyUser = fileman.loadUser(info.getUserFile());

        taskCount = testCopyUser.getTheTasks().size();
        for (ParentTask p : testCopyUser.getTheTasks()){
            taskAssignedSectionCount = taskAssignedSectionCount + p.getParentSections().size();
        }

        listMan.deconstructMasterList();
        assertTrue(o.getActiveUser().getTheTasks().size() == taskCount);

        int activeSectioncount = 0;
        for (ParentTask p : o.getActiveUser().getTheTasks()){
            activeSectioncount = activeSectioncount + p.getParentSections().size();
        }
        assertTrue(activeSectioncount == taskAssignedSectionCount);

        o.logoutUser();
    }



}
