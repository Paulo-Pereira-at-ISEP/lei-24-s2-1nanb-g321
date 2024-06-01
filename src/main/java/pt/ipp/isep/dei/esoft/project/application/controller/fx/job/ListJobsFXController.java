package pt.ipp.isep.dei.esoft.project.application.controller.fx.job;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.io.IOException;
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/job/JobMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Job Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}