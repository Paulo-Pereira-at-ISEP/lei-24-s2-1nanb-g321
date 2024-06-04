package pt.ipp.isep.dei.esoft.project.application.controller.fx.task;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

public class TaskMenuFXController {

    @FXML
    private Button createTaskButton;

    @FXML
    private Button listTasksButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        createTaskButton.setOnAction(event -> handleCreateTask());
        listTasksButton.setOnAction(event -> handleListTasks());
        backButton.setOnAction(event -> handleBack());
    }

    private void handleCreateTask() {
        UtilsFX.bottonControl("/fxml/task/CreateTask.fxml", createTaskButton, "Create Task");
    }

    private void handleListTasks() {
        UtilsFX.bottonControl("/fxml/task/ListTasks.fxml", listTasksButton, "List Tasks");
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/employee/GSMMenu.fxml", backButton, "GSM Menu");
    }
}
