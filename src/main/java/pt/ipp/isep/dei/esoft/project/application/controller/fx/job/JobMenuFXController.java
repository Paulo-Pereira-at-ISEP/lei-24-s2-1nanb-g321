package pt.ipp.isep.dei.esoft.project.application.controller.fx.job;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

public class JobMenuFXController {

    @FXML
    private Button createJobButton;

    @FXML
    private Button listJobsButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        createJobButton.setOnAction(event -> handleCreateJob());
        listJobsButton.setOnAction(event -> handleListJobs());
        backButton.setOnAction(event -> handleBack());
    }

    private void handleCreateJob() {
        UtilsFX.bottonControl("/fxml/job/CreateJob.fxml", createJobButton, "Create Job");
    }

    private void handleListJobs() {
        UtilsFX.bottonControl("/fxml/job/ListJobs.fxml", listJobsButton, "List Jobs");
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/employee/HRMMenu.fxml", backButton, "HRM Menu");
    }
}
