/**
 * Abstract class for the UI commands. Provides the instance of UIManager and ensures each command contains an execute() function.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Commands;
import Cs2263.Project.Orchestrator;

public interface UICommand{

    Orchestrator orchestrator = UIManager.getInstance().getOrchestrator();

    public abstract void execute();
}
