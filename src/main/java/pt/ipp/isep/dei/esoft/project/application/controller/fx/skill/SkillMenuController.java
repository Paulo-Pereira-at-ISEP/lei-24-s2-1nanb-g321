package pt.ipp.isep.dei.esoft.project.application.controller.fx.skill;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SkillMenuController {

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/skill/CreateSkill.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) createSkillButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Create Skill");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleListSkills() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/skill/ListSkills.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) listSkillsButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("List Skills");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/HRMMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("HRM Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
