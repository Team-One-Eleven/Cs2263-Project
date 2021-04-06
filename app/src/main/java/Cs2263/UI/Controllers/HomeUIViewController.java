/**
 * The controller for the HomeUI FXML document. Provides functionality to the buttons, allowing them to create and send
 * command objects to the UIController.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.Project.User;
import Cs2263.Project.listable.lists.Section;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.listable.tasks.ParentTask;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.util.LinkedList;

public class HomeUIViewController extends UIViewController {


    /**
     * Below is all the JavaFX objects defined in the FXML documents for the home page of the UI.
     * This is a JavaFX controller, so it is loaded when the FXML document is loaded, and the controls are
     * bound to these objects so the UI can function.
     */


    //Task, list, and section gridpanes
    private GridPane taskGridpane;
    private GridPane listGridpane;
    private GridPane sectionGridpane;
    private DialogPane firstLastDialog;


    //List view controls
    @FXML private Tab fxTasksButton;
    @FXML private Tab fxCompletedButton;
    @FXML private Tab fxOverdueButton;
    @FXML private ListView<String> fxTaskList;
    @FXML private ListView<String> fxCompletedList;
    @FXML private ListView<String> fxOverdueList;

    //Search controls
    @FXML private TextField fxSearchTextField;
    @FXML private Button fxSearchButton;

    //User Controls
    @FXML private Button fxUserButton;
    @FXML private Button fxSettingsButton;

    //Always visible buttons
    @FXML private Button fxNewTaskButton;
    @FXML private Button fxNewSectionButton;
    @FXML private Button fxNewListButton;


    //Main window Gridpane
    @FXML private GridPane fxMainWindowGridPane;



    /**
     * showTask(), showSection(), and showList() change the internal gridpane to the right of the task lists to reflect
     * the task, section, and scene contexts.
     */

    //TODO very hacky, seems like a good spot for a state pattern

    @FXML private void showTask(){
        for (final Node n : fxMainWindowGridPane.getChildren()) {
            if (n != null && n.getId() != null) {
                if(n.getId().equals("fxListViewGridPane")
                        || n.getId().equals("fxSectionViewGridPane")
                        || n.getId().equals("fxTaskViewGridPane")) {
                    System.out.println("Test task");
                    this.fxMainWindowGridPane.getChildren().remove(n);
                    break;
                }
            }
        }
        fxMainWindowGridPane.add(taskGridpane,1,1);
    }
    @FXML private void showSection(){
        for (final Node n : fxMainWindowGridPane.getChildren()) {
            if (n != null && n.getId() != null) {
                if(n.getId().equals("fxListViewGridPane")
                        || n.getId().equals("fxSectionViewGridPane")
                        || n.getId().equals("fxTaskViewGridPane")) {
                    System.out.println("Test section");
                    this.fxMainWindowGridPane.getChildren().remove(n);
                    break;
                }
            }
        }
        fxMainWindowGridPane.add(sectionGridpane,1,1);
    }

    @FXML private void showList(){
        for (final Node n : fxMainWindowGridPane.getChildren()) {
            if (n != null && n.getId() != null) {
                if(n.getId().equals("fxListViewGridPane")
                        || n.getId().equals("fxSectionViewGridPane")
                        || n.getId().equals("fxTaskViewGridPane")) {
                    System.out.println("Test list");
                    this.fxMainWindowGridPane.getChildren().remove(n);
                    break;
                }
            }
        }
        fxMainWindowGridPane.add(listGridpane,1,1);
    }

    public void addTask(){
        //TODO create and execute AddTaskCommand when complete
    }


    /**
     * Loads the user info from the currently active user and updates the list views.
     *
     */
    public void loadUserInfo(){
        User activeUser = orchestrator.getActiveUser();
        //if(activeUser.getFirstName() == "" || activeUser.getLastName() == ""){ }
        LinkedList<ToDoList> masterList = orchestrator.getMasterList();


        for(ToDoList l :masterList){
            fxTaskList.getItems().add(l.getTitle());
            for(Section s: l.getSections()){
                fxTaskList.getItems().add(" " + s.getTitle());
                for(ParentTask t: s.getTasks()){
                    fxTaskList.getItems().add("  " + t.getTitle());
                }
            }
        }
    }

    public void setDialogScene(){
    }


    public HomeUIViewController(){

    }

    /**
     * These methods are called by the UIView when loaded, to give the controller access to the loaded
     * item contexts and their controllers. This is so a new context gridpane doesn't need to be created on each click,
     * it can just be overwritten.
     *
     * @param g  A gridpane object which is an item context
     */

    //TODO It may make more sense to create a new context gridpane each time, come back to this.
    public void setTaskGridpane(GridPane g){
        this.taskGridpane = g;
    }

    public void setListGridpane(GridPane g){
        this.listGridpane = g;
    }

    public void setSectionGridpane(GridPane g){
        this.sectionGridpane = g;
    }

    public void setDialogGridpane(DialogPane d){this.firstLastDialog = d;}


}
