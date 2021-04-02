/**
 * Controller for the dialog box that requests the first and last name from the user
 *
 * @Author Braydon Spaulding
 */

package Cs2263.UI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FirstLastDialogController extends UIViewController {

    @FXML TextField fxFirstName;
    @FXML TextField fxLastName;
    @FXML Button fxFirstLastOK;
    @FXML Label fxFirstLastLabel;

}
