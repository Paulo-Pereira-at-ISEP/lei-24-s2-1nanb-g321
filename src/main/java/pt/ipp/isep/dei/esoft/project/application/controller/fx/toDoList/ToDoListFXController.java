package pt.ipp.isep.dei.esoft.project.application.controller.fx.toDoList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

public class ToDoListFXController {

    @FXML
    private Button createEntryButton;

    @FXML
    private Button listEntryButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        createEntryButton.setOnAction(event -> handleCreateEntry());
        listEntryButton.setOnAction(event -> handleListEntry());
        backButton.setOnAction(event -> handleBack());
    }

    private void handleCreateEntry() {
        UtilsFX.bottonControl("/fxml/toDoList/CreateEntry.fxml", createEntryButton, "Create Entry");
    }

    private void handleListEntry() {
        UtilsFX.bottonControl("/fxml/toDoList/ListEntry.fxml", listEntryButton, "List Entry's");
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/employee/GSMMenu.fxml", backButton, "GSM Menu");
    }
}

