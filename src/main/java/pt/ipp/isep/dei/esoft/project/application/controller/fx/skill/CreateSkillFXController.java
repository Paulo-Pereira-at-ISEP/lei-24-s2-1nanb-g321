package pt.ipp.isep.dei.esoft.project.application.controller.fx.skill;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

public class CreateSkillFXController {

    @FXML
    private TextField skillNameField;

    @FXML
    private TextField skillDescriptionField;

    @FXML
    private Button backButton;

    private final CreateSkillController controller;

    public CreateSkillFXController() {
        this.controller = new CreateSkillController();
    }

    @FXML
    private void handleCreateSkill() {
        String skillName = skillNameField.getText();
        if (UtilsFX.readOnlyLetters(skillName, "Name")) return;

        String skillDescription = skillDescriptionField.getText();
        if (UtilsFX.readOnlyLetters(skillDescription, "Description")) return;

        Skill skill = controller.createSkill(skillName, skillDescription);

        if (skill != null) {
            showAlert(Alert.AlertType.INFORMATION, "Skill Created", "Skill successfully created!");
            skillNameField.clear();
            skillDescriptionField.clear();
        } else {
            showAlert(Alert.AlertType.ERROR, "Skill Not Created", "Skill not created!");
        }
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/skill/SkillMenu.fxml", backButton, "Skill Menu");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
