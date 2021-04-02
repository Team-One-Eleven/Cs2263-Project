/**
 * Abstract class for the UI commands. Provides the instance of UIManager and ensures each command contains an execute() function.
 *
 * @author Braydon Spaulding
 */

package Cs2263.UI.Commands;

import Cs2263.UI.UIManager;

public interface UICommand{

    UIManager uiManager = UIManager.getInstance();

    public abstract void execute();
}
