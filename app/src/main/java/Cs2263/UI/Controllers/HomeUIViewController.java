/**
 * The controller for the HomeUI FXML document. Provides functionality to the buttons, allowing them to create and send
 * command objects to the UIController.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.Project.User;
import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.listable.ListableType;
import Cs2263.Project.listable.lists.Section;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.listable.tasks.ParentTask;
import Cs2263.Project.listable.tasks.TaskPriority;
import Cs2263.UI.ListableItemCell;
import Cs2263.UI.TreeItemCell;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HomeUIViewController extends UIViewController {


    /**
     * Below is all the JavaFX objects defined in the FXML documents for the home page of the UI.
     * This is a JavaFX controller, so it is loaded when the FXML document is loaded, and the controls are
     * bound to these objects so the UI can function.
     */
    //Task List Section Gridpane Locations
    private String UI_TASK_GRIDPANE = "/TaskItemContext.fxml";
    private String UI_LIST_GRIDPANE = "/ListItemContext.fxml";
    private String UI_SECTION_GRIDPANE = "/SectionItemContext.fxml";

    //Dialog Locations
    private String FIRST_LAST_DIALOG = "/FirstLastNameDialog.fxml";

    //Load each FXML file for different item states(task, list, section)
    FXMLLoader taskContentFxmlLoader = new FXMLLoader(getClass().getResource(UI_TASK_GRIDPANE));
    FXMLLoader listContentFxmlLoader = new FXMLLoader(getClass().getResource(UI_LIST_GRIDPANE));
    FXMLLoader sectionContentFxmlLoader = new FXMLLoader(getClass().getResource(UI_SECTION_GRIDPANE));

    //Load dialog FXML files
    FXMLLoader firstLastDialogLoader = new FXMLLoader(getClass().getResource(FIRST_LAST_DIALOG));


    //Task, list, and section controllers
    private TaskContextUIController taskContextUIController;
    private ListContextUIController listContextUIController;
    private SectionContextUIController sectionContextUIController;

    //Dialog Controllers
    private FirstLastDialogController firstLastDialogController;

    //Dialog Boxes
    DialogPane firstLastDialog;

    //Task, list, and section gridpanes
    private GridPane taskGridpane;
    private GridPane listGridpane;
    private GridPane sectionGridpane;

    //List view controls
    @FXML private TreeView<ListableItem> fxTaskTree;
    @FXML private Tab fxCompletedTab;
    @FXML private Tab fxOverdueTab;
    @FXML private Tab fxArchivedTab;
    @FXML private ListView<ListableItem> fxTaskList;
    @FXML private ListView<ListableItem> fxCompletedList;
    @FXML private ListView<ListableItem> fxOverdueList;
    @FXML private ListView<ListableItem> fxArchivedList;

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

    @FXML private GridPane fxTutorialGridpane;

    //Main window Gridpane
    @FXML private GridPane fxMainWindowGridPane;

    //Observable Lists for ListViews
    ObservableList<ListableItem> taskListObservableList = FXCollections.observableArrayList();
    ObservableList<ListableItem> completeListObservableList = FXCollections.observableArrayList();
    ObservableList<ListableItem> overdueListObservableList = FXCollections.observableArrayList();

    //TreeItem Roots
    TreeItem<ListableItem> taskListRoot = new TreeItem<>();

    public HomeUIViewController() {
        try {
            this.taskGridpane = taskContentFxmlLoader.load();
            this.listGridpane = listContentFxmlLoader.load();
            this.sectionGridpane = sectionContentFxmlLoader.load();
            this.firstLastDialog = firstLastDialogLoader.load();

            this.firstLastDialogController = firstLastDialogLoader.getController();
            this.taskContextUIController = taskContentFxmlLoader.getController();
            this.listContextUIController = listContentFxmlLoader.getController();
            this.sectionContextUIController = sectionContentFxmlLoader.getController();
        }
        catch (IOException e){
            System.out.printf("IO Exception in %s%n",this.getClass().getName());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        //loadUserTaskList();
    }



    /**
     * showTask(), showSection(), and showList() change the internal gridpane to the right of the task lists to reflect
     * the task, section, and scene contexts.
     */

    @FXML private void showTask(){
        clearView();
        fxMainWindowGridPane.add(taskGridpane,1,1);
    }
    @FXML private void showSection(){
        clearView();
        fxMainWindowGridPane.add(sectionGridpane,1,1);
    }

    @FXML private void showList(){
        clearView();
        fxMainWindowGridPane.add(listGridpane,1,1);
    }

    private void clearView(){
        for (final Node n : fxMainWindowGridPane.getChildren()) {
            if (n != null && n.getId() != null) {
                if(n.getId().equals("fxListViewGridPane")
                        || n.getId().equals("fxSectionViewGridPane")
                        || n.getId().equals("fxTaskViewGridPane")
                        || n.getId().equals("fxListViewGridPane")
                        || n.getId().equals("fxTutorialGridpane")){
                    this.fxMainWindowGridPane.getChildren().remove(n);
                    break;
                }
            }
        }
    }

    public void addTask(){
        //TODO create and execute AddTaskCommand when complete
    }


    /**
     * Loads the user info from the currently active user and updates the list views.
     *
     */
    @FXML public void loadUserTaskList() {

//        LinkedList<ToDoList> masterList = orchestrator.getMasterList();
//        if(masterList == null){
//            throw new NullPointerException();
//        }

        ParentTask task1 = new ParentTask();
        task1.setTitle("Task 1");
        task1.setDescription("Task 1 vibes");
        task1.setPriority(TaskPriority.High);

        ParentTask task2 = new ParentTask();
        task2.setTitle("Task 2");
        task2.setDescription("Task 2 vibes");
        task2.setPriority(TaskPriority.Medium);

        ParentTask task3 = new ParentTask();
        task3.setTitle("Task 3");
        task3.setDescription("Task 3 vibes");
        task3.setPriority(TaskPriority.Low);

        Section section1 = new Section();
        section1.setTitle("Section 1");
        section1.setDescription("Section 1 vibes");

        Section section2 = new Section();
        section2.setTitle("Section 2");
        section2.setDescription("Section 2 vibes");

        section1.getTasks().add(task1);
        section1.getTasks().add(task2);
        section2.getTasks().add(task3);

        ToDoList list1 = new ToDoList();
        list1.setTitle("List 1");
        list1.setDescription("List 1 vibes");

        list1.getSections().add(section1);
        list1.getSections().add(section2);











        ArrayList<ListableItem> masterList = new ArrayList<>();

        masterList.add(list1);







        fxTaskList.setItems(taskListObservableList);

//        fxTaskList.setCellFactory(new Callback<ListView<ListableItem>, ListCell<ListableItem>>() {
//            @Override
//            public ListableItemCell<ListableItem> call(ListView<ListableItem> param) {
//                return new ListableItemCell<>();
//            }
//        });


    }

    public void checkFirstLastNameExists(){
        User activeUser = orchestrator.getActiveUser();
        //if(activeUser.getFirstName() == "" || activeUser.getLastName() == ""){ }
    }

    public void setDialogScene(){
    }

    private ObservableList<ListableItem> buildObservableList(ObservableList<ListableItem> a){
        ObservableList<ListableItem> returnList = FXCollections.observableArrayList();
        for(ListableItem item : a){
            if(item.getType() == ListableType.List){
                ToDoList toDoList = (ToDoList) item;
                ObservableList<ListableItem> newList = FXCollections.observableArrayList(toDoList);
                returnList.addAll(buildObservableList(newList));
            }
            else if(item.getType() == ListableType.Section){

            }
        }
    }

    private TreeItem<ListableItem> buildTree(LinkedList<ListableItem> list){
        for(ListableItem item : list){
            if(item.getType() == ListableType.List){
                ToDoList toDoList = (ToDoList) item;
                if(toDoList.isArchived()){

                }
                else{

                }
            }
        }
    }




    private void openFirstLastDialog(){

    }


    @FXML private void setSelectedItem(){
        ListableItem item = (ListableItem) fxTaskList.getSelectionModel().getSelectedItem();
        if(item == null){
            return;
        }
        else if(item.getType() == ListableType.List){
            ToDoList list = (ToDoList) item;
            listContextUIController.setData(item,list.getTitle(),list.getDescription(),list.isArchived());

        }
        showList();
    }


}
