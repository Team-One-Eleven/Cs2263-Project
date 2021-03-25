package Cs2263.UI;

public class UIController {




    //private Orchestrator orchestrator = Orchestrator.getInstance();
    private UICommand currentCommand;
    private UIState uiState;
    private DirectoryState dirState;
    private ItemViewState itemViewState;



    public void setCommand(UICommand u){
        this.currentCommand = u;
    }

    public void executeCommand(){
        currentCommand.execute();
    }
}
