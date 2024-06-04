package pt.ipp.isep.dei.esoft.project.application.controller.fx.collaborator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

public class CollaboratorMenuFXController {

    @FXML
    private Button createCollaboratorButton;

    @FXML
    private Button listCollaboratorsButton;

    @FXML
    private Button assignSkillsButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        createCollaboratorButton.setOnAction(event -> handleCreateCollaborator());
        listCollaboratorsButton.setOnAction(event -> handleListCollaborators());
        assignSkillsButton.setOnAction(event -> handleAssignSkills());
        backButton.setOnAction(event -> handleBack());
    }

    private void handleCreateCollaborator() {
        UtilsFX.bottonControl("/fxml/collaborator/CreateCollaborator.fxml", createCollaboratorButton, "Create Collaborator");
    }

    private void handleListCollaborators() {
        UtilsFX.bottonControl("/fxml/collaborator/ListCollaborators.fxml", listCollaboratorsButton, "List Collaborators");
    }

    private void handleAssignSkills() {
        UtilsFX.bottonControl("/fxml/collaborator/SelectCollaborator.fxml", assignSkillsButton, "Assign Skills Collaborators");
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/employee/HRMMenu.fxml", backButton, "HRM Menu");
    }
}
