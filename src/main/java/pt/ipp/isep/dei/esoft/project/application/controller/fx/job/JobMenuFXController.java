package pt.ipp.isep.dei.esoft.project.application.controller.fx.job;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/job/CreateJob.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) createJobButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Create Job");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleListJobs() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/job/ListJobs.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) listJobsButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("List Jobs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/HRMMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("HRM Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
