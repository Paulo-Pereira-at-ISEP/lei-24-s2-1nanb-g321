package pt.ipp.isep.dei.esoft.project.application.controller.fx.employee;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

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
    private Button logoutButton;

    // Inicializa o controlador, pode adicionar ações aos botões aqui
    @FXML
    private void initialize() {
        skillsButton.setOnAction(event -> handleSkills());
        jobsButton.setOnAction(event -> handleJobs());
        managerButton.setOnAction(event -> handleManager());
        collaboratorButton.setOnAction(event -> handleCollaborator());
        teamButton.setOnAction(event -> handleTeam());
        logoutButton.setOnAction(event -> handleLogout());
    }

    private void handleSkills() {
        UtilsFX.bottonControl("/fxml/skill/SkillMenu.fxml", skillsButton, "Skills Menu");
    }

    private void handleJobs() {
        UtilsFX.bottonControl("/fxml/job/JobMenu.fxml", jobsButton, "Jobs Menu");
    }

    private void handleManager() {
        UtilsFX.bottonControl("/fxml/manager/ManagerMenu.fxml", managerButton, "Manager Menu");
    }

    private void handleCollaborator() {
        UtilsFX.bottonControl("/fxml/collaborator/CollaboratorMenu.fxml", collaboratorButton, "Collaborator Menu");
    }

    private void handleTeam() {
        UtilsFX.bottonControl("/fxml/team/TeamMenu.fxml", teamButton, "Team Menu");
    }

    private void handleLogout() {
        UtilsFX.bottonControl("/fxml/LoginView.fxml", logoutButton, "Login Menu");
    }
}
