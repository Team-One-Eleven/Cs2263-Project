package Cs2263.Project.tools;

import Cs2263.Project.Orchestrator;
import Cs2263.Project.listable.lists.Section;
import Cs2263.Project.listable.lists.ToDoList;
import Cs2263.Project.listable.tasks.ChildTask;
import Cs2263.Project.listable.tasks.ParentTask;
import Cs2263.Project.listable.tasks.TaskStatus;
import Cs2263.Project.user.User;

import java.util.Date;
import java.util.LinkedList;


public class ListManager {
    private Orchestrator o;

    public ListManager(Orchestrator o){
        this.o = o;
    }
    public void constructMasterList(){
        LinkedList<ToDoList> masterList = o.getMasterList();
        User activeUser = o.getActiveUser();

        masterList.addAll(activeUser.getTheLists());

        LinkedList<ParentTask> unListed = new LinkedList<>();

        for (ParentTask toAdd : activeUser.getTheTasks()){
            for (ToDoList toSearch: masterList){
                nextListDownAddTask(toSearch, toAdd);
            }
            if (!toAdd.getParentSections().isEmpty()){
                toAdd.getParentSections().clear();
                unListed.add(toAdd);
            }

        }

        if (!unListed.isEmpty()){
            ToDoList lostAndFound = o.getItemFactory().makeToDOList();
            lostAndFound.setTitle("Unlisted");
            lostAndFound.getDefaultSection().getTasks().addAll(unListed);
            masterList.addLast(lostAndFound);
        }

        activeUser.getTheTasks().clear();

    }
    private void nextListDownAddTask(ToDoList toSearch, ParentTask toAdd){
        if (!toAdd.getParentSections().isEmpty()){
            for (Section secSearch: toSearch.getSections()){
                LinkedList<String> toRemove = new LinkedList<>();
                for (String id: toAdd.getParentSections()){
                    if (id == secSearch.getId()){
                        secSearch.getTasks().add(toAdd);
                        toRemove.add(id);
                    }
                }
                toAdd.getParentSections().removeAll(toRemove);
                for (ToDoList nextList : secSearch.getLists()){
                    nextListDownAddTask(nextList, toAdd);
                }
            }
        }
    }

    public void deconstructMasterList(){
        o.getMasterList().addAll(o.getActiveUser().getTheLists());

        for (ToDoList toSearch: o.getMasterList()){
            nextListDownRemove(toSearch);
        }


    }
    private void nextListDownRemove(ToDoList toSearch){
        for (Section secSearch: toSearch.getSections()){
            for (ParentTask t : secSearch.getTasks()){
                t.getParentSections().add(secSearch.getId());
                o.getActiveUser().getTheTasks().add(t);
            }
            secSearch.getTasks().clear();
            for (ToDoList nextList : secSearch.getLists()){
                nextListDownRemove(nextList);
            }
        }
    }

    public void checkDueDates(){
        if (o.getActiveUser().getTheTasks().isEmpty()){
            deconstructMasterList();
        }
        for (ParentTask t : o.getActiveUser().getTheTasks()){
            if ((t.getDueDate().before(new Date())) & (t.getStatus() == TaskStatus.incomplete)){
                t.setStatus(TaskStatus.overdue);
            }
        }
    }


}
