package pt.ipp.isep.dei.esoft.project.application.controller.fx.toDoList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.*;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;

public class CreateEntryFXController {

    private final CreateEntryController entryController = new CreateEntryController();
    private final CreateGreenSpacesController greenSpacesController = new CreateGreenSpacesController();
    private final CreateTaskController taskController = new CreateTaskController();
    public static final ArrayList<String> urgency = new ArrayList<>(List.of("Low", "Medium", "High"));

    @FXML
    private ListView<GreenSpace> greenSpaceListView;

    @FXML
    private ListView<Task> taskListView;

    @FXML
    private ComboBox<String> urgencyComboBox;

    @FXML
    private TextField durationField;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        listGreenSpaces();
        listTasks();
        urgencyComboBox.getItems().addAll(urgency);
    }

    private void listGreenSpaces() {
        List<GreenSpace> greenSpaces = greenSpacesController.getAllGreenSpaces();
        greenSpaceListView.getItems().setAll(greenSpaces);
    }

    private void listTasks() {
        List<Task> tasks = taskController.getAllTasks();
        taskListView.getItems().setAll(tasks);
    }

    @FXML
    private void handleCreateEntry() {
        GreenSpace greenSpace = greenSpaceListView.getSelectionModel().getSelectedItem();
        Task task = taskListView.getSelectionModel().getSelectedItem();
        String urgency = urgencyComboBox.getValue();
        String durationText = durationField.getText();

        if (greenSpace == null || task == null || urgency == null || durationText.isEmpty()) {
            UtilsFX.showAlert(Alert.AlertType.ERROR,"Error", "All fields must be filled");
            return;
        }

        try {
            int duration = Integer.parseInt(durationText);
            Entry entry = entryController.createEntry(task, urgency, duration, greenSpace);
            clearFields();
        } catch (NumberFormatException e) {
            UtilsFX.showAlert(Alert.AlertType.ERROR,"Error", "Duration must be a valid number");
        }
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/toDoList/ToDoListMenu.fxml", backButton, "To Do List Menu");
    }

    private void clearFields() {
        greenSpaceListView.getSelectionModel().clearSelection();
        taskListView.getSelectionModel().clearSelection();
        urgencyComboBox.setValue(null);
        durationField.clear();
    }
}