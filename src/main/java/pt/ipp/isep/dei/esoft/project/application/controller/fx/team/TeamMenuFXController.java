package pt.ipp.isep.dei.esoft.project.application.controller.fx.team;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/team/CreateTeam.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) createTeamButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Create Team");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleListTeams() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/team/ListTeams.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) listTeamsButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("List Teams");
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
