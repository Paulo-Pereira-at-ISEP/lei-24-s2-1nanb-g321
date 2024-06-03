package pt.ipp.isep.dei.esoft.project.application.controller.fx.skill;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.IOException;

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/skill/SkillMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Skill Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
