package pt.ipp.isep.dei.esoft.project.application.controller.fx.job;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateJobController;
import pt.ipp.isep.dei.esoft.project.domain.Job;

import java.io.IOException;

public class CreateJobFXController {

    @FXML
    private TextField jobNameField;

    @FXML
    private TextField jobDescriptionField;

    @FXML
    private Button backButton;

    private final CreateJobController controller;

    public CreateJobFXController() {
        this.controller = new CreateJobController();
    }

    @FXML
    private void handleCreateJob() {
        String jobName = jobNameField.getText();
        String jobDescription = jobDescriptionField.getText();

        Job job = controller.createJob(jobName, jobDescription);

        if (job != null) {
            showAlert(Alert.AlertType.INFORMATION, "Job Created", "Job successfully created!");
            jobNameField.clear();
            jobDescriptionField.clear();
        } else {
            showAlert(Alert.AlertType.ERROR, "Job Not Created", "Job not created!");
        }
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
