package pt.ipp.isep.dei.esoft.project.application.controller.fx.collaborator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;

public class AssignSkillsFXController {

    private Collaborator collaborator;
    private final CreateSkillController skillController = new CreateSkillController();

    @FXML
    private ListView<Skill> skillsListView;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
        loadSkills();
    }

    private void loadSkills() {
        List<Skill> skills = new ArrayList<>(List.copyOf(skillController.getAllSkills()));

        // Get the skills already possessed by the employee
        List<Skill> collaboratorSkills = collaborator.getSkills();

        // Remove skills that the employee already has
        skills.removeAll(collaboratorSkills);

        skillsListView.getItems().setAll(skills);
        skillsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void handleSave() {
        List<Skill> selectedSkills = new ArrayList<>(skillsListView.getSelectionModel().getSelectedItems());

        // Add the selected skills to the collaborator and save changes to the database.
        collaborator.addSkill((ArrayList<Skill>) selectedSkills);
        UtilsFX.bottonControl("/fxml/collaborator/SelectCollaborator.fxml", saveButton, "Collaborator Menu");
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/collaborator/SelectCollaborator.fxml", backButton, "Collaborator Menu");
    }

}