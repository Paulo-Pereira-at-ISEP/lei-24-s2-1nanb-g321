package pt.ipp.isep.dei.esoft.project.application.controller.fx.team;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateTeamFXController {

    private CreateSkillController skillController = new CreateSkillController();
    private GenerateTeamController controller = new GenerateTeamController();

    @FXML
    private TextField minTeamField;

    @FXML
    private TextField maxTeamField;

    @FXML
    private ListView<Skill> skillListView;

    @FXML
    private Button backButton;

    public CreateTeamFXController() {
        // Construtor padrão
    }

    @FXML
    private void initialize() {
        List<Skill> skills = skillController.getAllSkills();

        // Adicionar todas as habilidades ao ListView
        skillListView.setItems(FXCollections.observableArrayList(skills));

        // Permitir seleção múltipla
        skillListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void handleCreateTeam() {
        try {
            int minTeam = Integer.parseInt(minTeamField.getText());
            int maxTeam = Integer.parseInt(maxTeamField.getText());

            ArrayList<Skill> selectedSkills = new ArrayList<>(skillListView.getSelectionModel().getSelectedItems());

            Team team = controller.generateTeam(minTeam, maxTeam, selectedSkills);

            if (team != null) {
                showAlert(Alert.AlertType.INFORMATION, "Team Created", "Team successfully created!");
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Team Not Created", "Team not created!");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for team sizes.");
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/team/TeamMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Team Menu");
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

    private void clearFields() {
        maxTeamField.clear();
        minTeamField.clear();
        skillListView.getSelectionModel().clearSelection();
    }
}