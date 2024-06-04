package pt.ipp.isep.dei.esoft.project.application.controller.fx.team;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateTeamFXController {

    private final CreateSkillController skillController = new CreateSkillController();
    private final GenerateTeamController controller = new GenerateTeamController();

    @FXML
    private TextField minTeamField;

    @FXML
    private TextField maxTeamField;

    @FXML
    private TableView<Skill> skillTableView;

    @FXML
    private TableColumn<Skill, String> skillColumn;

    @FXML
    private TableColumn<Skill, Void> actionColumn;

    @FXML
    private TableColumn<Skill, Integer> countColumn;

    @FXML
    private Button backButton;

    private Map<Skill, Integer> selectedSkillsCount;

    @FXML
    private void initialize() {
        List<Skill> skills = skillController.getAllSkills();

        selectedSkillsCount = new HashMap<>();

        skillColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        countColumn.setCellValueFactory(param -> {
            Skill skill = param.getValue();
            return new ReadOnlyObjectWrapper<>(selectedSkillsCount.getOrDefault(skill, 0));
        });

        countColumn.setCellFactory(column -> new TableCell<Skill, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });

        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button addButton = new Button("Add");

            {
                addButton.setOnAction(event -> {
                    Skill skill = getTableView().getItems().get(getIndex());
                    selectedSkillsCount.put(skill, selectedSkillsCount.getOrDefault(skill, 0) + 1);
                    getTableView().refresh();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(addButton);
                }
            }
        });

        skillTableView.setItems(FXCollections.observableArrayList(skills));
    }

    @FXML
    private void handleCreateTeam() {
        try {
            int maxTeam = Integer.parseInt(maxTeamField.getText());
            int minTeam = Integer.parseInt(minTeamField.getText());

            List<Skill> selectedSkills = new ArrayList<>();
            for (Map.Entry<Skill, Integer> entry : selectedSkillsCount.entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    selectedSkills.add(entry.getKey());
                }
            }

            Team team = controller.createTeam(maxTeam, minTeam, (ArrayList<Skill>) selectedSkills);

            if (team != null && !team.getCollaborators().isEmpty()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/team/ShowCollaborators.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Show Collaborators");

                ShowCollaboratorsFXController showCollaboratorsController = loader.getController();
                showCollaboratorsController.setTeam(team);

                stage.showAndWait();

                if (showCollaboratorsController.isAccepted()) {
                    controller.addToRepository(team);
                    controller.colaboratorHasTeam(team);
                    UtilsFX.showAlert(Alert.AlertType.INFORMATION, "Team Created", "Team successfully created!");
                    clearFields();
                } else if (showCollaboratorsController.isRejected()) {
                    Team team1 = controller.createSecondTeam(maxTeam, minTeam, (ArrayList<Skill>) selectedSkills, team);

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
        UtilsFX.bottonControl("/fxml/team/TeamMenu.fxml", backButton, "Team Menu");
    }

    private void clearFields() {
        maxTeamField.clear();
        minTeamField.clear();
        selectedSkillsCount.clear();
        skillTableView.refresh();
    }
}