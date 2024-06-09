package pt.ipp.isep.dei.esoft.project.application.controller.fx.agenda;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryToAgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import java.util.List;

public class ListAgendaFXController {

    private final CreateEntryToAgendaController entryController = new CreateEntryToAgendaController();

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
        urgencyColumn.setMinWidth(150);
        durationColumn.setMinWidth(150);
        startTimeColumn.setMinWidth(150);
        endTimeColumn.setMinWidth(150);
        dateColumn.setMinWidth(150);
        statusColumn.setMinWidth(150);
        teamColumn.setMinWidth(150);

        listEntryFromToDoList();
    }

    private void listEntryFromToDoList() {
        List<Entry> entries = entryController.getAllEntries();
        entriesTable.getItems().setAll(entries);
    }


    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/agenda/AgendaMenu.fxml", backButton, "Agenda Menu");
    }
}