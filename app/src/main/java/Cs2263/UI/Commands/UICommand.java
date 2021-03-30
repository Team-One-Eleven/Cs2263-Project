/**
 * Abstract class for the UI commands. Provides the Orchestrator (the interface between the UI and Business Logic) for
 * use in commands, as well as ensuring each command contains an execute() function.
 * @author Braydon Spaulding
 */

package Cs2263.UI.Commands;

import Cs2263.Project.Orchestrator;

public interface UICommand {

    Orchestrator orchestrator = new Orchestrator();

    public abstract void execute();
}
