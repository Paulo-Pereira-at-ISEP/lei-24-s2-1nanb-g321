package pt.ipp.isep.dei.esoft.project.application.controller.fx.task;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/task/CreateTask.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) createTaskButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Create Task");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleListTasks() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/task/ListTasks.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) listTasksButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("List Tasks");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/GSMMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("GSM Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
