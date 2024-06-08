package pt.ipp.isep.dei.esoft.project.application.controller.fx.agenda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

public class AgendaMenuFXController {

    @FXML
    private Button addNewEntryButton;

    @FXML
    private Button listAgendaButton;

    @FXML
    private Button assignEntryToTeamButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        addNewEntryButton.setOnAction(event -> handleAddNewEntry());
        listAgendaButton.setOnAction(event -> handleListAgenda());
        assignEntryToTeamButton.setOnAction(event -> handleAssignEntryToTeam());
        backButton.setOnAction(event -> handleBack());
    }

    private void handleAddNewEntry() {
        UtilsFX.bottonControl("/fxml/agenda/AddNewEntry.fxml", addNewEntryButton, "Add New Entry to Agenda");
    }

    private void handleListAgenda() {
        UtilsFX.bottonControl("/fxml/agenda/ListAgenda.fxml", listAgendaButton, "List Agenda Entries");
    }

    private void handleAssignEntryToTeam() {
        UtilsFX.bottonControl("/fxml/agenda/AssignEntryToTeam.fxml", assignEntryToTeamButton, "Assign Entry To Team");
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/employee/GSMMenu.fxml", backButton, "GSM Menu");
    }
}
