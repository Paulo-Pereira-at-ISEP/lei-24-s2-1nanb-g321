package pt.ipp.isep.dei.esoft.project.application.controller.fx.collaborator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CollaboratorMenuFXController {

    @FXML
    private Button createCollaboratorButton;

    @FXML
    private Button listCollaboratorsButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        createCollaboratorButton.setOnAction(event -> handleCreateCollaborator());
        listCollaboratorsButton.setOnAction(event -> handleListCollaborators());
        backButton.setOnAction(event -> handleBack());
    }

    private void handleCreateCollaborator() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/collaborator/CreateCollaborator.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) createCollaboratorButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Create Collaborator");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleListCollaborators() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/collaborator/ListCollaborators.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) listCollaboratorsButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("List Collaborators");
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
