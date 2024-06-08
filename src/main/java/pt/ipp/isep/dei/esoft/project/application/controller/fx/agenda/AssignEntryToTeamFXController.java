package pt.ipp.isep.dei.esoft.project.application.controller.fx.agenda;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryToAgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.List;

public class AssignEntryToTeamFXController {

    private final CreateEntryToAgendaController entryController = new CreateEntryToAgendaController();
    private final GenerateTeamController teamController = new GenerateTeamController();

    @FXML
    private TableView<Entry> entriesTable;

    @FXML
    private TableColumn<Entry, String> greenSpaceColumn;

    @FXML
    private TableColumn<Entry, String> taskColumn;

    @FXML
    private TableColumn<Entry, String> urgencyColumn;

    @FXML
    private TableColumn<Entry, String> statusColumn;

    @FXML
    private TableColumn<Entry, String> dateColumn;

    @FXML
    private TableColumn<Entry, String> startTimeColumn;

    @FXML
    private TableColumn<Entry, String> endTimeColumn;



    @FXML
    private TableView<Team> teamTable;

    @FXML
    private TableColumn<Team, String> idTeamColumn;

    @FXML
    private TableColumn<Team, String> collaboratorColumn;

    @FXML
    private TableColumn<Team, String> skillsColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        greenSpaceColumn.setCellValueFactory(new PropertyValueFactory<>("greenSpace"));
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        urgencyColumn.setCellValueFactory(new PropertyValueFactory<>("urgencyDegree"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        entriesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        greenSpaceColumn.setMinWidth(150);
        taskColumn.setMinWidth(150);
        urgencyColumn.setMinWidth(150);
        statusColumn.setMinWidth(150);
        dateColumn.setMinWidth(150);
        startTimeColumn.setMinWidth(100);
        endTimeColumn.setMinWidth(100);

        listEntryFromToDoList();

        idTeamColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        collaboratorColumn.setCellValueFactory(new PropertyValueFactory<>("collaborators"));
        skillsColumn.setCellValueFactory(new PropertyValueFactory<>("skills"));

        teamTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idTeamColumn.setMinWidth(50);
        collaboratorColumn.setMinWidth(250);
        skillsColumn.setMinWidth(700);

        listTeams();
    }

    private void listTeams() {
        List<Team> teams = teamController.getAllTeams();
        teamTable.getItems().setAll(teams);
    }

    private void listEntryFromToDoList() {
        List<Entry> entries = entryController.getAllEntrys();
        entriesTable.getItems().setAll(entries);
    }

    @FXML
    private void handleAssignEntryToTeam() {
        Entry entry = entriesTable.getSelectionModel().getSelectedItem();
        Team team = teamTable.getSelectionModel().getSelectedItem();

        entry.setTeam(team);

        if (team != null) {
            UtilsFX.showAlert(Alert.AlertType.INFORMATION, "Team Assigned","Team successfully assigned!");
            clearFields();
        } else {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Team Not Assigned","Team not assigned!");
        }
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/agenda/AgendaMenu.fxml", backButton, "Agenda Menu");
    }

    private void clearFields() {
        entriesTable.getSelectionModel().clearSelection();
        teamTable.getSelectionModel().clearSelection();
    }
}

