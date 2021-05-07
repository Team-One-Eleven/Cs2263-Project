/**
 * The controller for the HomeUI FXML document. Provides functionality to the buttons, allowing them to create and send
 * command objects to the UIController.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import Cs2263.Project.Orchestrator;
import Cs2263.Project.listable.ListableItem;
import Cs2263.Project.listable.ListableType;
import Cs2263.Project.listable.lists.Section;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.listable.tasks.ChildTask;
import Cs2263.Project.listable.tasks.ParentTask;
import Cs2263.Project.listable.tasks.TaskStatus;
import Cs2263.UI.UIManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    @FXML private Tab fxTodayTab;
    @FXML private ListView<ListableItem> fxTodayList;
    @FXML private ListView<ListableItem> fxUpcomingList;
    @FXML private ListView<ListableItem> fxOverdueList;
    @FXML private ListView<ListableItem> fxCompletedList;
    @FXML private TreeView<ListableItem> fxArchivedTree;

    //Search controls
    @FXML private TextField fxSearchTextField;
    @FXML private Button fxSearchButton;

    //User Controls
    @FXML private Button fxUserButton;
    @FXML private Button fxSettingsButton;
    @FXML private Button fxLogout;
    @FXML private Button fxSaveExit;

    //Always visible buttons
    @FXML private Button fxNewTaskButton;
    @FXML private Button fxNewSectionButton;
    @FXML private Button fxNewListButton;

    //Gridpane before item is selected
    @FXML private GridPane fxTutorialGridpane;

    //Main window Gridpane
    @FXML private GridPane fxMainWindowGridPane;

    //Login Scene
    private Scene loginScene;

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

        //Controller exchange
        this.taskContextUIController = taskContentFxmlLoader.getController();
        this.listContextUIController = listContentFxmlLoader.getController();
        this.sectionContextUIController = sectionContentFxmlLoader.getController();
        taskContextUIController.setHomeUIViewController(this);
        listContextUIController.setHomeUIViewController(this);
        sectionContextUIController.setHomeUIViewController(this);
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
        try {
            item = fxTaskTree.getSelectionModel().getSelectedItem().getValue();
        }
        catch (NullPointerException e){
            item = fxTaskTree.getRoot().getValue();
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
        try {
            item = fxTaskTree.getSelectionModel().getSelectedItem().getValue();
        }
        catch (NullPointerException e){
            item = fxTaskTree.getRoot().getValue();
        }
        if(item.getType() == ListableType.List){
            ToDoList list = (ToDoList) item;
            list.getSections().add(orchestrator.getItemFactory().makeSection());
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

    @FXML private void addList(){
        ListableItem item;
        try {
            item = fxTaskTree.getSelectionModel().getSelectedItem().getValue();
        }
        catch (NullPointerException e){
            item = fxTaskTree.getRoot().getValue();
        }
        if(item.getType() == ListableType.List){
            ToDoList list = (ToDoList) item;
            list.getDefaultSection().getLists().add(orchestrator.getItemFactory().makeToDOList());
        }
        else if(item.getType() == ListableType.Section){
            Section section = (Section) item;
            section.getLists().add(orchestrator.getItemFactory().makeToDOList());
        }
        else{
            return;
        }
        refreshTree();
    }

    @FXML public void removeList(){
        ListableItem item;
        try{
            item = fxTaskTree.getSelectionModel().getSelectedItem().getValue();
        }
        catch (NullPointerException e){
            return;
        }
        if(item.getType() == ListableType.List){
            Section parentSection = (Section) fxTaskTree.getSelectionModel().getSelectedItem().getParent().getValue();
            parentSection.getLists().remove(item);
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
        ArrayList<ToDoList> masterList = orchestrator.getMasterList();
        if(masterList == null){
            throw new NullPointerException();
        }
        fxTaskTree.setRoot(buildTree(orchestrator.getMasterList().get(0)));
    }

    //This feels gross
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
                if(task.getStatus() == TaskStatus.incomplete){
                    if(task.getDueDate().isEqual(LocalDate.now())){
                        fxTodayList.getItems().add(task);
                    }
                    else if(task.getDueDate().isAfter(LocalDate.now())){
                        fxUpcomingList.getItems().add(task);
                    }
                    else if(task.getDueDate().isBefore(LocalDate.now())){
                        task.setStatus(TaskStatus.overdue);
                        fxOverdueList.getItems().add(task);
                    }
                }
                else if(task.getStatus() == TaskStatus.complete){
                    fxCompletedList.getItems().add(task);
                    return null;
                }
                else if(task.getStatus() == TaskStatus.overdue){
                    if(task.getDueDate().isEqual(LocalDate.now())){
                        task.setStatus(TaskStatus.incomplete);
                        fxTodayList.getItems().add(task);
                    }
                    else if(task.getDueDate().isAfter(LocalDate.now())){
                        task.setStatus(TaskStatus.incomplete);
                        fxUpcomingList.getItems().add(task);
                    }
                    else{
                        fxOverdueList.getItems().add(task);
                    }
                }

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
                if(child.getStatus() == TaskStatus.incomplete){
                    if(child.getDueDate().isEqual(LocalDate.now())){
                        fxTodayList.getItems().add(child);
                    }
                    else if(child.getDueDate().isAfter(LocalDate.now())){
                        fxUpcomingList.getItems().add(child);
                    }
                    else if(child.getDueDate().isBefore(LocalDate.now())){
                        child.setStatus(TaskStatus.overdue);
                        fxOverdueList.getItems().add(child);
                    }
                }
                else if(child.getStatus() == TaskStatus.complete){
                    fxCompletedList.getItems().add(child);
                    return null;
                }
                else if(child.getStatus() == TaskStatus.overdue){
                    if(child.getDueDate().isEqual(LocalDate.now())){
                        child.setStatus(TaskStatus.incomplete);
                        fxTodayList.getItems().add(child);
                    }
                    else if(child.getDueDate().isAfter(LocalDate.now())){
                        child.setStatus(TaskStatus.incomplete);
                        fxUpcomingList.getItems().add(child);
                    }
                    else{
                        fxOverdueList.getItems().add(child);
                    }
                }
                returnTree.setExpanded(true);
                return returnTree;
            }
        return null;
    }

    public void refreshTree(){
        fxTaskTree.getRoot().getChildren().clear();
        fxTodayList.getItems().clear();
        fxUpcomingList.getItems().clear();
        fxOverdueList.getItems().clear();
        fxCompletedList.getItems().clear();
        fxTaskTree.getRoot().setExpanded(true);
        fxTaskTree.setRoot(buildTree(orchestrator.getMasterList().get(0)));
    }

    @FXML private void setSelectedItem() {
        ListableItem item;
        try {
            if(fxTasksTab.isSelected()){
                item = fxTaskTree.getSelectionModel().getSelectedItem().getValue();
            }
            else if(fxTodayTab.isSelected()){
                item = fxTodayList.getSelectionModel().getSelectedItem();
            }
            else if(fxUpcomingTab.isSelected()){
                item = fxUpcomingList.getSelectionModel().getSelectedItem();
            }
            else if(fxOverdueTab.isSelected()){
                item = fxOverdueList.getSelectionModel().getSelectedItem();
            }
            else if(fxArchivedTab.isSelected()){
                item = fxArchivedTree.getSelectionModel().getSelectedItem().getValue();
            }
            else if(fxCompletedTab.isSelected()){
                item = fxCompletedList.getSelectionModel().getSelectedItem();
            }
            else{
                return;
            }
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
            taskContextUIController.setData(task, task.getTitle(), task.getDescription(), task.getDueDate());
            showTask();
        }
        else if (item.getType() == ListableType.ChildTask){
            ChildTask task = (ChildTask) item;
            taskContextUIController.setData(task, task.getTitle(), task.getDescription(), task.getDueDate());
            showTask();
        }
    }

    @FXML private void logoutUser(ActionEvent event){
        clearView();
        orchestrator.logoutUser();
        Platform.runLater(() -> {
            Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
            s.setScene(loginScene);
        });
    }

    @FXML private void saveExit(){
        clearView();
        orchestrator.logoutUser();
        orchestrator.exit();
        uiManager.getView().exit();
    }

    public void setLoginScene(Scene s) {
        this.loginScene = s;
    }
}
