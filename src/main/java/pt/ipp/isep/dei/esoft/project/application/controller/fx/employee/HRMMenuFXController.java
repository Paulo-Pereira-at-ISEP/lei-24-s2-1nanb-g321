package pt.ipp.isep.dei.esoft.project.application.controller.fx.employee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HRMMenuFXController {

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
        // Lógica para redirecionar para a UI de Jobs
    }

    private void handleManager() {
        // Lógica para redirecionar para a UI de Manager
    }

    private void handleCollaborator() {
        // Lógica para redirecionar para a UI de Collaborator
    }

    private void handleTeam() {
        // Lógica para redirecionar para a UI de Team
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
}
