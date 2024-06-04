package pt.ipp.isep.dei.esoft.project.application.controller.fx.task;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateTaskController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Task;

import java.util.List;

public class ListTasksFXController {

    private final CreateTaskController taskController = new CreateTaskController();

    @FXML
    private TableView<Task> tasksTable;

    @FXML
    private TableColumn<Task, String> nameColumn;

    @FXML
    private TableColumn<Task, String> descriptionColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        tasksTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        nameColumn.setMinWidth(150);
        descriptionColumn.setMinWidth(300);

        listTasks();
    }

    private void listTasks() {
        List<Task> tasks = taskController.getAllTasks();
        tasksTable.getItems().setAll(tasks);
    }

    @FXML
    private void handleBack() {
        UtilsFX.backControl("/fxml/task/TaskMenu.fxml", backButton, "Task Menu");
    }
}