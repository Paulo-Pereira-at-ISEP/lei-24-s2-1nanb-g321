package pt.ipp.isep.dei.esoft.project.application.controller.fx.team;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.collaborator.TeamMemberView;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListTeamFXController {

    private final GenerateTeamController teamController = new GenerateTeamController();

    @FXML
    private TableView<TeamMemberView> teamsTable;

    @FXML
    private TableColumn<TeamMemberView, Integer> idTeamColumn;

    @FXML
    private TableColumn<TeamMemberView, String> collaboratorColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        idTeamColumn.setCellValueFactory(new PropertyValueFactory<>("teamId"));
        collaboratorColumn.setCellValueFactory(new PropertyValueFactory<>("collaboratorName"));

        teamsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idTeamColumn.setMinWidth(50);
        collaboratorColumn.setMinWidth(150);

        listTeams();
    }

    private void listTeams() {
        List<Team> teams = teamController.getAllTeams();
        List<TeamMemberView> teamMemberViews = new ArrayList<>();

        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            int teamId = i + 1; // ID incremental
            for (Collaborator collaborator : team.getCollaborators()) {
                teamMemberViews.add(new TeamMemberView(teamId, collaborator.getName()));
            }
        }

        teamsTable.setItems(FXCollections.observableArrayList(teamMemberViews));
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
}