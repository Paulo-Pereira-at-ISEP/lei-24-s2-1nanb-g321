package pt.ipp.isep.dei.esoft.project.application.controller.fx.agenda;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryToAgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListAllMyAgendaFXController {

    private final CreateEntryToAgendaController entryController = new CreateEntryToAgendaController();
    public static final ArrayList<String> urgency = new ArrayList<>(List.of("Low", "Medium", "High"));

    @FXML
    private ComboBox<String> urgencyComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableView<Entry> entriesTable;

    @FXML
    private TableColumn<Entry, String> greenSpaceColumn;

    @FXML
    private TableColumn<Entry, String> taskColumn;

    @FXML
    private TableColumn<Entry, String> urgencyColumn;

    @FXML
    private TableColumn<Entry, String> durationColumn;

    @FXML
    private TableColumn<Entry, String> startTimeColumn;

    @FXML
    private TableColumn<Entry, String> endTimeColumn;

    @FXML
    private TableColumn<Entry, String> dateColumn;

    @FXML
    private TableColumn<Entry, String> statusColumn;

    @FXML
    private TableColumn<Entry, String> teamColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        urgencyComboBox.getItems().addAll(urgency);

        greenSpaceColumn.setCellValueFactory(new PropertyValueFactory<>("greenSpace"));
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        urgencyColumn.setCellValueFactory(new PropertyValueFactory<>("urgencyDegree"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));

        entriesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        greenSpaceColumn.setMinWidth(150);
        taskColumn.setMinWidth(150);
        urgencyColumn.setMinWidth(30);
        durationColumn.setMinWidth(20);
        startTimeColumn.setMinWidth(30);
        endTimeColumn.setMinWidth(30);
        dateColumn.setMinWidth(50);
        statusColumn.setMinWidth(30);
        teamColumn.setMinWidth(450);

        listEntryFromToDoList();
    }

    private void listEntryFromToDoList() {
        List<Entry> entries = entryController.getAllEntries();
        List<Entry> sortedEntries = entryController.getSortedEntriesByDate(entries);
        List<Entry> myEntries = new ArrayList<>();

        for (Entry entry : sortedEntries) {
            if (entry.getTeam() != null){
                for (Collaborator collaborator : entry.getTeam().getCollaborators()) {
                    if (Objects.equals(collaborator.getEmail(), ApplicationSession.getInstance().getCurrentSession().getUserEmail())){
                        myEntries.add(entry);
                    }
                }
            }
        }
        entriesTable.getItems().setAll(myEntries);
    }

    @FXML
    private void handleFilterButtonAction() {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Date");
            alert.setHeaderText(null);
            alert.setContentText("The start date cannot be later than the end date.");
            alert.showAndWait();
            return;
        }

        List<Entry> entries = entryController.getAllEntries();
        List<Entry> myEntries = new ArrayList<>();

        for (Entry entry : entries) {
            if (entry.getTeam() != null){
                for (Collaborator collaborator : entry.getTeam().getCollaborators()) {
                    if (Objects.equals(collaborator.getEmail(), ApplicationSession.getInstance().getCurrentSession().getUserEmail())){
                        myEntries.add(entry);
                    }
                }
            }
        }

        List<Entry> filteredEntries = new ArrayList<>();

        String selectedUrgency = urgencyComboBox.getValue();

        for (Entry entry : myEntries) {
            boolean matchesUrgency = selectedUrgency == null || entry.getUrgencyDegree().equals(selectedUrgency);
            boolean matchesStartDate = startDate == null || !entry.getEntryDate().isBefore(startDate);
            boolean matchesEndDate = endDate == null || !entry.getEntryDate().isAfter(endDate);

            if (matchesUrgency && matchesStartDate && matchesEndDate) {
                filteredEntries.add(entry);
            }
        }

        entriesTable.getItems().setAll(filteredEntries);
    }

    @FXML
    private void handleClearFilterButtonAction() {
        listEntryFromToDoList();
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/employee/CollaboratorMenu.fxml", backButton, "Collaborator Menu");
    }
}