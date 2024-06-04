package pt.ipp.isep.dei.esoft.project.application.controller.fx.skill;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

public class SkillMenuFXController {

    @FXML
    private Button createSkillButton;

    @FXML
    private Button listSkillsButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        createSkillButton.setOnAction(event -> handleCreateSkill());
        listSkillsButton.setOnAction(event -> handleListSkills());
        backButton.setOnAction(event -> handleBack());
    }

    private void handleCreateSkill() {
        UtilsFX.bottonControl("/fxml/skill/CreateSkill.fxml", createSkillButton, "Create Skill");
    }

    private void handleListSkills() {
        UtilsFX.bottonControl("/fxml/skill/ListSkills.fxml", listSkillsButton, "List Skills");
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/employee/HRMMenu.fxml", backButton, "HRM Menu");
    }
}
