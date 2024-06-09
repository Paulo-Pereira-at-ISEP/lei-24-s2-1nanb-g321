package pt.ipp.isep.dei.esoft.project.application.controller.fx.agenda;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryToAgendaController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddNewEntryFXController {

    private final CreateEntryToAgendaController entryController = new CreateEntryToAgendaController();

    public static final ArrayList<String> hours = new ArrayList<>(List.of("8", "9","10","11","12","13","14","15","16"));

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
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> hourComboBox;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        greenSpaceColumn.setCellValueFactory(new PropertyValueFactory<>("greenSpace"));
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        urgencyColumn.setCellValueFactory(new PropertyValueFactory<>("urgencyDegree"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        entriesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        greenSpaceColumn.setMinWidth(150);
        taskColumn.setMinWidth(200);
        urgencyColumn.setMinWidth(150);
        durationColumn.setMinWidth(300);

        listEntryFromToDoList();
        hourComboBox.getItems().addAll(hours);
    }

    private void listEntryFromToDoList() {
        List<Entry> entries = entryController.getAllToDoListEntrys();
        entriesTable.getItems().setAll(entries);
    }

    @FXML
    private void handleAddNewEntryToAgenda() {
        Entry entry = entriesTable.getSelectionModel().getSelectedItem();
        LocalDate date = datePicker.getValue();
        String hour = hourComboBox.getValue();

        if (entry == null || date == null || hour == null) {
            // Mostrar mensagem de erro ao usuário
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Entry");
            alert.setContentText("Please, select an entry, date and hour.");
            alert.showAndWait();
            return;
        }else{
            UtilsFX.showAlert(Alert.AlertType.INFORMATION, "Entry Added","Entry successfully added to Agenda!");
            clearFields();
        }

        entry.setEntryDate(date);
        entry.setHour(Integer.parseInt(hour));

        entryController.createEntry(entry.getName(), entry.getDescription(), entry.getUrgencyDegree(), entry.getDuration(), entry.getGreenSpace(), entry.getEntryDate(), entry.getHour());

    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/agenda/AgendaMenu.fxml", backButton, "Agenda Menu");
    }

    private void clearFields() {
        entriesTable.getSelectionModel().clearSelection();
        datePicker.setValue(null);
        hourComboBox.setValue(null);
    }
}