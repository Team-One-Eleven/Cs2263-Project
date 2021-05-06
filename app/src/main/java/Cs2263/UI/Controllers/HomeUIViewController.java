/**
 * The controller for the HomeUI FXML document. Provides functionality to the buttons, allowing them to create and send
 * command objects to the UIController.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.Project.Orchestrator;
import Cs2263.Project.User;
import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.listable.ListableType;
import Cs2263.Project.listable.lists.Section;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.listable.tasks.ChildTask;
import Cs2263.Project.listable.tasks.ParentTask;
import Cs2263.Project.listable.tasks.TaskPriority;
import Cs2263.UI.UIManager;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HomeUIViewController extends UIViewController {


    /**
     * Below is all the JavaFX objects defined in the FXML documents for the home page of the UI.
     * This is a JavaFX controller, so it is loaded when the FXML document is loaded, and the controls are
     * bound to these objects so the UI can function.
     */

    //Get Orchestrator
    Orchestrator Orchestrator = UIManager.getInstance().getOrchestrator();

    //Task List Section Gridpane Locations
    private final String UI_TASK_GRIDPANE = "/TaskItemContext.fxml";
    private final String UI_LIST_GRIDPANE = "/ListItemContext.fxml";
    private final String UI_SECTION_GRIDPANE = "/SectionItemContext.fxml";

    //Dialog Locations
    private final String FIRST_LAST_DIALOG = "/FirstLastNameDialog.fxml";

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
    @FXML private Tab fxTasksTab;
    @FXML private Tab fxUpcomingTab;
    @FXML private Tab fxCompletedTab;
    @FXML private Tab fxOverdueTab;
    @FXML private Tab fxArchivedTab;
    @FXML private TreeView<ListableItem> fxTodayTree;
    @FXML private TreeView<ListableItem> fxUpcomingTree;
    @FXML private ListView<ListableItem> fxOverdueTree;
    @FXML private ListView<ListableItem> fxCompletedTree;
    @FXML private ListView<ListableItem> fxArchivedTree;

    //List TreeView Roots
    private TreeItem<ListableItem> taskTreeRoot;

    //TODO remove this test variable
    private ToDoList testList;

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

    public HomeUIViewController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.taskGridpane = taskContentFxmlLoader.load();
            this.listGridpane = listContentFxmlLoader.load();
            this.sectionGridpane = sectionContentFxmlLoader.load();
            this.firstLastDialog = firstLastDialogLoader.load();
        }
        catch (IOException e){
            System.out.printf("IO Exception in %s%n",this.getClass().getName());
            System.out.printf("%s%n",e.getMessage());
        }
            this.firstLastDialogController = firstLastDialogLoader.getController();
            this.taskContextUIController = taskContentFxmlLoader.getController();
            this.listContextUIController = listContentFxmlLoader.getController();
            this.sectionContextUIController = sectionContentFxmlLoader.getController();


        taskContextUIController.setHomeUIViewController(this);
        listContextUIController.setHomeUIViewController(this);
        sectionContextUIController.setHomeUIViewController(this);
        loadUserTaskList();
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

    //This clears the view so an item can be shown
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

    @FXML private void addTask(){
        ListableItem item;
        try{
            item = fxTaskTree.getSelectionModel().getSelectedItem().getValue();
        }
        catch (NullPointerException e){
            return;
        }
        if(item.getType() == ListableType.List){
            ToDoList list = (ToDoList) item;
            list.getDefaultSection().addTask(Orchestrator.getItemFactory().makeParentTask());
        }
        else if(item.getType() == ListableType.Section){
            Section section = (Section) item;
            section.addTask(Orchestrator.getItemFactory().makeParentTask());
        }
        else if(item.getType() == ListableType.ParentTask){
            ParentTask task = (ParentTask) item;
            task.getChildTasks().add(Orchestrator.getItemFactory().makeChildTask());
        }
        else{
            return;
        }
        refreshTree();
    }

    @FXML public void removeTask(){
        ListableItem itemVal;
        ListableItem parentVal;
        try{
            itemVal = fxTaskTree.getSelectionModel().getSelectedItem().getValue();
            parentVal = fxTaskTree.getSelectionModel().getSelectedItem().getParent().getValue();
        }
        catch (NullPointerException e){
            return;
        }
        if(parentVal.getType() == ListableType.Section){
            Section section= (Section) parentVal;
            section.getTasks().remove(itemVal);
            clearView();
        }
        else{
            return;
        }
        refreshTree();
    }

    @FXML private void addSection(){
        ListableItem item;
        try{
            item = fxTaskTree.getSelectionModel().getSelectedItem().getValue();
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
            return;
        }
        if(item.getType() == ListableType.List){
            ToDoList list = (ToDoList) item;
            list.getSections().add(orchestrator.getItemFactory().makeSection());
            System.out.println("Added Section");
        }
        else{
            return;
        }
        refreshTree();
    }

    @FXML public void removeSection(){
        ListableItem item;
        try{
            item = fxTaskTree.getSelectionModel().getSelectedItem().getValue();
        }
        catch (NullPointerException e){
            return;
        }
        if(item.getType() == ListableType.Section){
            ToDoList parentList = (ToDoList) fxTaskTree.getSelectionModel().getSelectedItem().getParent().getValue();
            parentList.getSections().remove(item);
        }
        else{
            return;
        }
        refreshTree();
    }




    /**
     * Loads the user info from the currently active user and updates the list views.
     *
     */
    @FXML public void loadUserTaskList() {
//        ArrayList<ToDoList> masterList = orchestrator.getMasterList();
//        if(masterList == null){
//            throw new NullPointerException();
//        }
//        for(ListableItem i : masterList){
//            taskTreeRoot.getChildren().add(buildTree(i));
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
        list1.initDefaultSection(section1);
        list1.setDescription("List 1 vibes");

        list1.getSections().add(section1);
        list1.getSections().add(section2);

        testList = list1;

        taskTreeRoot = buildTree(testList);

        fxTaskTree.setRoot(taskTreeRoot);

    }

    public boolean checkFirstLastNameExists(){
        User activeUser = Orchestrator.getActiveUser();
        if(activeUser.getFirstName().length() == 0|| activeUser.getLastName().length() == 0){
            return false;
        }
        else return true;
    }


    private TreeItem<ListableItem> buildTree(ListableItem root){
        TreeItem<ListableItem> returnTree;
            if(root.getType() == ListableType.List){
                ToDoList toDoList = (ToDoList) root;
                returnTree = new TreeItem<>(toDoList);

                ArrayList<ListableItem> sectionArray = new ArrayList<>();
                for(Section s : toDoList.getSections()){
                    sectionArray.add(s);
                }

                for(ListableItem section : sectionArray){
                    returnTree.getChildren().add(buildTree(section));
                }

                returnTree.setExpanded(true);
                return returnTree;
            }
            else if(root.getType() == ListableType.Section){
                Section section = (Section) root;
                returnTree = new TreeItem<>(section);

                ArrayList<ListableItem> taskArray = new ArrayList<>();
                for(ParentTask sectionTasks : section.getTasks()){
                    taskArray.add(sectionTasks);
                }

                ArrayList<ListableItem> listArray = new ArrayList<>();
                for(ToDoList sectionLists : section.getLists()){
                    listArray.add(sectionLists);
                }

                for(ListableItem t : taskArray){
                    returnTree.getChildren().add(buildTree(t));
                }
                for(ListableItem l : listArray){
                    returnTree.getChildren().add(buildTree(l));
                }

                returnTree.setExpanded(true);
                return returnTree;
            }
            else if(root.getType() == ListableType.ParentTask){
                ParentTask task = (ParentTask) root;
                returnTree = new TreeItem<>(task);

                ArrayList<ListableItem> childArray = new ArrayList<>();
                for(ChildTask child : task.getChildTasks()){
                    childArray.add(child);
                }

                for(ListableItem child : childArray){
                    returnTree.getChildren().add(buildTree(child));
                }

                returnTree.setExpanded(true);
                return returnTree;
            }
            else if(root.getType() == ListableType.ChildTask){
                ChildTask child = (ChildTask) root;
                returnTree = new TreeItem<>(child);


                returnTree.setExpanded(true);
                return returnTree;
            }
        return null;
    }

    public void refreshTree(){
        taskTreeRoot.getChildren().clear();
        taskTreeRoot = buildTree(testList);
        taskTreeRoot.setExpanded(true);
        fxTaskTree.setRoot(taskTreeRoot);
    }




    public void openFirstLastDialog(){
        Scene scene = new Scene(firstLastDialog, 600, 300);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }


    @FXML private void setSelectedItem() {
        ListableItem item;
        try {
            item = fxTaskTree.getSelectionModel().getSelectedItem().getValue();
        } catch (NullPointerException e) {
            return;
        }
        if (item.getType() == ListableType.List) {
            ToDoList list = (ToDoList) item;
            listContextUIController.setData(list, list.getTitle(), list.getDescription(), list.isArchived());
            showList();
        } else if (item.getType() == ListableType.Section) {
            Section section = (Section) item;
            sectionContextUIController.setData(section, section.getTitle(), section.getDescription());
            showSection();
        } else if (item.getType() == ListableType.ParentTask) {
            ParentTask task = (ParentTask) item;
            taskContextUIController.setData(task, task.getTitle(), task.getDescription());
            showTask();
        }
        else if (item.getType() == ListableType.ChildTask){
            ChildTask task = (ChildTask) item;
            taskContextUIController.setData(task, task.getTitle(), task.getDescription());
            showTask();
        }
    }
}
