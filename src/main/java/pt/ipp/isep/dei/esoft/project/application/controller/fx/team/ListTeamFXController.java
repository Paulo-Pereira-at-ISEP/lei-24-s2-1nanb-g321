package pt.ipp.isep.dei.esoft.project.application.controller.fx.team;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import java.util.ArrayList;
import java.util.List;

public class ListTeamFXController {

    private final GenerateTeamController teamController = new GenerateTeamController();

    @FXML
    private TableView<TeamMemberView> teamsTable;

    @FXML
    private TableColumn<TeamMemberView, Integer> idTeamColumn;

    @FXML
    private TableColumn<TeamMemberView, String> collaboratorColumn;

    @FXML
    private TableColumn<TeamMemberView, String> emailColumn;

    @FXML
    private TableColumn<TeamMemberView, String> jobColumn;

    @FXML
    private TableColumn<TeamMemberView, String> skillsColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        idTeamColumn.setCellValueFactory(new PropertyValueFactory<>("teamId"));
        collaboratorColumn.setCellValueFactory(new PropertyValueFactory<>("collaboratorName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("collaboratorEmail"));
        jobColumn.setCellValueFactory(new PropertyValueFactory<>("collaboratorJob"));
        skillsColumn.setCellValueFactory(new PropertyValueFactory<>("collaboratorSkills"));

        teamsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idTeamColumn.setMinWidth(20);
        collaboratorColumn.setMinWidth(150);
        emailColumn.setMinWidth(150);
        jobColumn.setMinWidth(150);
        skillsColumn.setMinWidth(470);

        listTeams();
    }

    private void listTeams() {
        List<Team> teams = teamController.getAllTeams();
        List<TeamMemberView> teamMemberViews = new ArrayList<>();

        for (Team team : teams) {
            int teamId = team.getId(); // Use o ID existente da equipe
            for (Collaborator collaborator : team.getCollaborators()) {
                teamMemberViews.add(new TeamMemberView(teamId, collaborator.getName(), collaborator.getEmail(), collaborator.getJob(), collaborator.getSkills()));
            }
        }

        teamsTable.setItems(FXCollections.observableArrayList(teamMemberViews));
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/team/TeamMenu.fxml", backButton, "Team Menu");
    }
}