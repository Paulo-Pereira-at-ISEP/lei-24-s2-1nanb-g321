package pt.ipp.isep.dei.esoft.project.application.controller.fx.agenda;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryToAgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.GenerateTeamController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.time.LocalTime;
import java.util.ArrayList;
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

        greenSpaceColumn.setMinWidth(240);
        taskColumn.setMinWidth(240);
        urgencyColumn.setMinWidth(20);
        statusColumn.setMinWidth(20);
        dateColumn.setMinWidth(40);
        startTimeColumn.setMinWidth(20);
        endTimeColumn.setMinWidth(20);

        listEntryFromAgenda();

        idTeamColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        collaboratorColumn.setCellValueFactory(new PropertyValueFactory<>("collaborators"));
        skillsColumn.setCellValueFactory(team -> {
            Team t = team.getValue();
            List<String> skills = new ArrayList<>();
            for (var collaborator : t.getCollaborators()) {
                for (Skill skill : collaborator.getSkills()) {
                    if (!skills.contains(skill.getName())) {
                        skills.add(skill.getName());
                    }
                }
            }
            return new ReadOnlyStringWrapper(String.join(", ", skills));
        });

        teamTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idTeamColumn.setMinWidth(10);
        collaboratorColumn.setMinWidth(230);
        skillsColumn.setMinWidth(450);

        listTeams();
    }

    private void listTeams() {
        List<Team> teams = teamController.getAllTeams();
        teamTable.getItems().setAll(teams);
    }

    private void listEntryFromAgenda() {
        List<Entry> entries = entryController.getAllEntries();
        List<Entry> entriesWithoutTeam = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getTeam() == null) {
                entriesWithoutTeam.add(entry);
            }
        }
        entriesTable.getItems().setAll(entriesWithoutTeam);
    }

    @FXML
    private void handleAssignEntryToTeam() {
        Entry entry = entriesTable.getSelectionModel().getSelectedItem();
        Team team = teamTable.getSelectionModel().getSelectedItem();

        if (team == null || entry == null) {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Selection Error", "Please select both an entry and a team!");
            return;
        }

        List<Entry> entriesByTeam = entryController.getEntriesByTeam(team);

        if (isTeamAvailable(entriesByTeam, entry)) {
            entry.setTeam(team);
            UtilsFX.showAlert(Alert.AlertType.INFORMATION, "Team Assigned", "Team successfully assigned!");
            clearFields();
        } else {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Team Not Available", "The selected team is not available at the given time!");
        }
    }

    private boolean isTeamAvailable(List<Entry> entriesByTeam, Entry newEntry) {
        LocalTime newEntryStartTime = newEntry.getStartTime();
        LocalTime newEntryEndTime = newEntry.getEndTime();

        for (Entry entry : entriesByTeam) {
            if (entry.getEntryDate().equals(newEntry.getEntryDate())) {
                LocalTime entryStartTime = entry.getStartTime();
                LocalTime entryEndTime = entry.getEndTime();

                if ((newEntryStartTime.isBefore(entryEndTime) && newEntryEndTime.isAfter(entryStartTime)) ||
                        (newEntryStartTime.equals(entryStartTime) && newEntryEndTime.equals(entryEndTime))) {
                    return false;
                }
            }
        }
        return true;
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/agenda/AgendaMenu.fxml", backButton, "Agenda Menu");
    }

    private void clearFields() {
        entriesTable.getSelectionModel().clearSelection();
        teamTable.getSelectionModel().clearSelection();
        listEntryFromAgenda();
    }
}