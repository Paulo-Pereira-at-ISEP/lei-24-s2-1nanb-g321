package pt.ipp.isep.dei.esoft.project.application.controller.fx.employee;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        UtilsFX.bottonControl("/fxml/agenda/ListMyAgenda.fxml", tasksButton, "List My Tasks Menu");
    }

    private void handleTasks() {
        UtilsFX.showAlert(Alert.AlertType.INFORMATION, "Task Complete Menu", "Not implemented yet");
    }

    private void handleLogout() {
        UtilsFX.bottonControl("/fxml/LoginView.fxml", logoutButton, "Login Menu");
    }

    @FXML
    private void handleClose() {
        UtilsFX.closeControl(closeButton);
    }
}
