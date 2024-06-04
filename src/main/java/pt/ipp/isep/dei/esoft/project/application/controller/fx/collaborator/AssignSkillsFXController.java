package pt.ipp.isep.dei.esoft.project.application.controller.fx.collaborator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;

public class AssignSkillsFXController {

    private Collaborator collaborator;
    private CreateSkillController skillController = new CreateSkillController();

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
        for (Skill skill : collaboratorSkills) {
            skills.remove(skill);
        }
        skillsListView.getItems().setAll(skills);
    }

    @FXML
    private void handleSave() {
        List<Skill> selectedSkills = skillsListView.getSelectionModel().getSelectedItems();
        // Add the selected skills to the collaborator and save changes to the database.
        List<Skill> collaboratorSkills = collaborator.getSkills();
        ArrayList<Skill> newSkills = new ArrayList<>(selectedSkills);
        newSkills.addAll(collaboratorSkills);
        newSkills.addAll(selectedSkills);

        collaborator.addSkill(newSkills);
        UtilsFX.bottonControl("/fxml/collaborator/SelectCollaborator.fxml", saveButton, "Collaborator Menu");
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/collaborator/SelectCollaborator.fxml", backButton, "Collaborator Menu");
    }

}