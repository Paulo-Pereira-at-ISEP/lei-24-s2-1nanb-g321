package pt.ipp.isep.dei.esoft.project.application.controller.fx.toDoList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateEntryController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.util.List;

public class ListEntrysFXController {

    private final CreateEntryController entryController = new CreateEntryController();

    @FXML
    private TableView<Entry> entrysTable;

    @FXML
    private TableColumn<Entry, String> greenSpaceColumn;

    @FXML
    private TableColumn<Entry, String> taskColumn;

    @FXML
    private TableColumn<Entry, String> urgencyColumn;

    @FXML
    private TableColumn<Entry, String> durationColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        greenSpaceColumn.setCellValueFactory(new PropertyValueFactory<>("greenSpace"));
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        urgencyColumn.setCellValueFactory(new PropertyValueFactory<>("urgencyDegree"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        entrysTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        greenSpaceColumn.setMinWidth(150);
        taskColumn.setMinWidth(200);
        urgencyColumn.setMinWidth(150);
        durationColumn.setMinWidth(300);

        listCollaborators();
    }

    private void listCollaborators() {
        List<Entry> entries = entryController.getAllEntrys();
        entrysTable.getItems().setAll(entries);
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/toDoList/ToDoListMenu.fxml", backButton, "To Do List Menu");
    }
}