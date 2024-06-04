package pt.ipp.isep.dei.esoft.project.application.controller.fx.team;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

import java.io.IOException;

public class TeamMenuFXController {

    @FXML
    private Button createTeamButton;

    @FXML
    private Button listTeamsButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        createTeamButton.setOnAction(event -> handleCreateTeam());
        listTeamsButton.setOnAction(event -> handleListTeams());
        backButton.setOnAction(event -> handleBack());
    }

    private void handleCreateTeam() {
        UtilsFX.bottonControl("/fxml/team/CreateTeam.fxml", createTeamButton, "Create Team");
    }

    private void handleListTeams() {
        UtilsFX.bottonControl("/fxml/team/ListTeams.fxml", listTeamsButton, "List Teams");
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/employee/HRMMenu.fxml", backButton, "HRM Menu");
    }
}
