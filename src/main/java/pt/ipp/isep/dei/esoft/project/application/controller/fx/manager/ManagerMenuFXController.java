package pt.ipp.isep.dei.esoft.project.application.controller.fx.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerMenuFXController {

    @FXML
    private Button createManagerButton;

    @FXML
    private Button listManagersButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        createManagerButton.setOnAction(event -> handleCreateManager());
        listManagersButton.setOnAction(event -> handleListManagers());
        backButton.setOnAction(event -> handleBack());
    }

    private void handleCreateManager() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/manager/CreateManager.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) createManagerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Create Manager");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleListManagers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/manager/ListManagers.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) listManagersButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("List Managers");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/HRMMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("HRM Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
