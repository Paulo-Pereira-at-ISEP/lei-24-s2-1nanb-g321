package pt.ipp.isep.dei.esoft.project.application.controller.fx.employee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;

import java.io.IOException;

public class HRMMenuFXController {

    private AuthenticationController authenticationController;

    @FXML
    private Button skillsButton;

    @FXML
    private Button jobsButton;

    @FXML
    private Button managerButton;

    @FXML
    private Button collaboratorButton;

    @FXML
    private Button teamButton;

    @FXML
    private Button tasksButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button closeButton;

    // Inicializa o controlador, pode adicionar ações aos botões aqui
    @FXML
    private void initialize() {
        skillsButton.setOnAction(event -> handleSkills());
        jobsButton.setOnAction(event -> handleJobs());
        managerButton.setOnAction(event -> handleManager());
        collaboratorButton.setOnAction(event -> handleCollaborator());
        teamButton.setOnAction(event -> handleTeam());
        tasksButton.setOnAction(event -> handleTasks());
        logoutButton.setOnAction(event -> handleLogout());
        closeButton.setOnAction(event -> handleClose());
    }

    private void handleSkills() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/skill/SkillMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) skillsButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Skills Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleJobs() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/job/JobMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) jobsButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Jobs Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleManager() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/manager/ManagerMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) managerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Manager Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCollaborator() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/collaborator/CollaboratorMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) collaboratorButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Collaborator Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleTeam() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/team/TeamMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) teamButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Team Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleTasks() {
        // Lógica para redirecionar para a UI de Tasks
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
