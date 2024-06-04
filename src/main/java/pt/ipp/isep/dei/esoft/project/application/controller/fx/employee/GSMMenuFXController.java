package pt.ipp.isep.dei.esoft.project.application.controller.fx.employee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

public class GSMMenuFXController {

    @FXML
    private Button greenSpacesButton;

    @FXML
    private Button tasksButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button closeButton;

    @FXML
    private void initialize() {
        greenSpacesButton.setOnAction(event -> handleGreenSpaces());
        tasksButton.setOnAction(event -> handletasks());
        logoutButton.setOnAction(event -> handleLogout());
        closeButton.setOnAction(event -> handleClose());
    }

    private void handleGreenSpaces() {
        UtilsFX.bottonControl("/fxml/greenSpace/GreenSpacesMenu.fxml", greenSpacesButton, "Green Spaces Menu");
    }

    private void handletasks() {
        UtilsFX.bottonControl("/fxml/task/TaskMenu.fxml", tasksButton, "Task Menu");
    }

    private void handleLogout() {
        UtilsFX.bottonControl("/fxml/LoginView.fxml", logoutButton, "Login Menu");
    }

    @FXML
    private void handleClose() {
        UtilsFX.closeControl(closeButton);
    }
}
