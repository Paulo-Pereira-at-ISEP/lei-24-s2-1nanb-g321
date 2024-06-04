package pt.ipp.isep.dei.esoft.project.application.controller.fx.employee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/greenSpace/GreenSpacesMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) greenSpacesButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Green Spaces Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handletasks() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/task/TaskMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) tasksButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Tasks Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClose() {
        // Close the current window
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
