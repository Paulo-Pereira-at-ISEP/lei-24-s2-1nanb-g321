package pt.ipp.isep.dei.esoft.project.application.controller.fx.employee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

public class CollaboratorMenuFXController {

    @FXML
    private Button listTasksButton;

    @FXML
    private Button tasksButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button closeButton;

    @FXML
    private void initialize() {
        listTasksButton.setOnAction(event -> handleListTasks());
        tasksButton.setOnAction(event -> handleTasks());
        logoutButton.setOnAction(event -> handleLogout());
        closeButton.setOnAction(event -> handleClose());
    }

    private void handleListTasks() {
        UtilsFX.bottonControl("/fxml/task/TaskMenu.fxml", tasksButton, "List Task Menu");
    }

    private void handleTasks() {
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
