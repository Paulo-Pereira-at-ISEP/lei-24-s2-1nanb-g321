package pt.ipp.isep.dei.esoft.project.application.controller.fx.collaborator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AssignSkillsFXController {

    private Collaborator collaborator;
    private CreateSkillController skillController = new CreateSkillController();

    @FXML
    private ListView<Skill> skillsListView;

    @FXML
    private Button saveButton;

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/collaborator/SelectCollaborator.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Collaborator Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}