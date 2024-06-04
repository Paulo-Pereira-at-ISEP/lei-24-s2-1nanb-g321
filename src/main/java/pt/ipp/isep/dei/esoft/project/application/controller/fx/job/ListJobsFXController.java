package pt.ipp.isep.dei.esoft.project.application.controller.fx.job;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import java.util.List;

public class ListJobsFXController {

    private final CreateJobController jobController = new CreateJobController();

    @FXML
    private TableView<Job> jobsTable;

    @FXML
    private TableColumn<Job, String> nameColumn;

    @FXML
    private TableColumn<Job, String> descriptionColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        jobsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        nameColumn.setMinWidth(150);
        descriptionColumn.setMinWidth(300);

        listJobs();
    }

    private void listJobs() {
        List<Job> jobs = jobController.getAllJobs();
        jobsTable.getItems().setAll(jobs);
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/job/JobMenu.fxml", backButton, "Job Menu");
    }
}