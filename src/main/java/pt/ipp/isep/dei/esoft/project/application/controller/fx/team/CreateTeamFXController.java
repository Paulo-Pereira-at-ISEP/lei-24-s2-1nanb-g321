package pt.ipp.isep.dei.esoft.project.application.controller.fx.team;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateTeamFXController {

    private final CreateSkillController skillController = new CreateSkillController();
    private final GenerateTeamController controller = new GenerateTeamController();

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
            int maxTeam = Integer.parseInt(maxTeamField.getText());
            int minTeam = Integer.parseInt(minTeamField.getText());

            ArrayList<Skill> selectedSkills = new ArrayList<>(skillListView.getSelectionModel().getSelectedItems());

            Team team = controller.createTeam(maxTeam, minTeam, selectedSkills);

            if (team != null && !team.getCollaborators().isEmpty()) {
                // Abre a nova janela para mostrar os colaboradores
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/team/ShowCollaborators.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Show Collaborators");

                ShowCollaboratorsFXController showCollaboratorsController = loader.getController();
                showCollaboratorsController.setTeam(team);

                stage.showAndWait();

                // Se a equipe for aceita, adicione ao repositório
                if (showCollaboratorsController.isAccepted()) {
                    controller.addToRepository(team);
                    controller.colaboratorHasTeam(team);
                    UtilsFX.showAlert(Alert.AlertType.INFORMATION, "Team Created", "Team successfully created!");
                    clearFields();
                } else if (showCollaboratorsController.isRejected()){
                    Team team1 = controller.createSecondTeam(maxTeam, minTeam, selectedSkills, team);

                    if (team1 != null && !team1.getCollaborators().isEmpty()) {

                        loader = new FXMLLoader(getClass().getResource("/fxml/team/ShowCollaborators.fxml"));
                        scene = new Scene(loader.load());
                        stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Show Collaborators");

                        showCollaboratorsController = loader.getController();
                        showCollaboratorsController.setTeam(team1);

                        stage.showAndWait();

                        if (showCollaboratorsController.isAccepted()) {
                            controller.addToRepository(team1);
                            controller.colaboratorHasTeam(team1);
                            UtilsFX.showAlert(Alert.AlertType.INFORMATION, "Team Created", "Team successfully created!");
                            clearFields();
                        } else {
                            UtilsFX.showAlert(Alert.AlertType.ERROR, "Team Not Created", "Team not created!");
                        }
                    } else {
                        UtilsFX.showAlert(Alert.AlertType.ERROR, "Team Not Created", "No more collaborators to show!");
                    }
                } else {
                    UtilsFX.showAlert(Alert.AlertType.ERROR, "Team Not Created", "Team not created!");
                }
            } else {
                UtilsFX.showAlert(Alert.AlertType.ERROR, "Team Not Created", "Team not created!");
            }
        } catch (NumberFormatException | IOException e) {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for team sizes.");
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

    private void clearFields() {
        maxTeamField.clear();
        minTeamField.clear();
        skillListView.getSelectionModel().clearSelection();
    }
}