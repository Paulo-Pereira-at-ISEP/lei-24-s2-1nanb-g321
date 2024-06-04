package pt.ipp.isep.dei.esoft.project.application.controller.fx.task;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.io.IOException;

public class CreateTaskFXController {

    @FXML
    private TextField taskNameField;

    @FXML
    private TextField taskDescriptionField;

    @FXML
    private Button backButton;

    private final CreateTaskController controller;

    public CreateTaskFXController() {
        this.controller = new CreateTaskController();
    }

    @FXML
    private void handleCreateTask() {
        String taskName = taskNameField.getText();
        if (UtilsFX.readOnlyLetters(taskName, "Name")) return;

        String taskDescription = taskDescriptionField.getText();
        if (UtilsFX.readOnlyLetters(taskDescription, "Description")) return;

        Task task = controller.createTask(taskName, taskDescription);

        if (task != null) {
            UtilsFX.showAlert(Alert.AlertType.INFORMATION, "Task Created", "Task successfully created!");
            taskNameField.clear();
            taskDescriptionField.clear();
        } else {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Task Not Created", "Task not created!");
        }
    }

    @FXML
    private void handleBack() {
        UtilsFX.backControl("/fxml/task/TaskMenu.fxml",backButton,"Task Menu");
    }

}
