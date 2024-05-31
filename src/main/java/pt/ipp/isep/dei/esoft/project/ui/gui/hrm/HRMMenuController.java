package pt.ipp.isep.dei.esoft.project.ui.gui.hrm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HRMMenuController {

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

    // Inicializa o controlador, pode adicionar ações aos botões aqui
    @FXML
    private void initialize() {
        skillsButton.setOnAction(event -> handleSkills());
        jobsButton.setOnAction(event -> handleJobs());
        managerButton.setOnAction(event -> handleManager());
        collaboratorButton.setOnAction(event -> handleCollaborator());
        teamButton.setOnAction(event -> handleTeam());
        tasksButton.setOnAction(event -> handleTasks());
    }

    private void handleSkills() {
        // Lógica para redirecionar para a UI de Skills
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
}
