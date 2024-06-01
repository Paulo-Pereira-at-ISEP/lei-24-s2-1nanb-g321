package pt.ipp.isep.dei.esoft.project.application.controller.fx.collaborator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateCollaboratorFXController {

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker dateOfBirthPicker;

    @FXML
    private DatePicker admissionDatePicker;

    @FXML
    private TextField addressField;

    @FXML
    private TextField mobileField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> idDocTypeComboBox;

    @FXML
    private TextField docTypeNumberField;

    @FXML
    private TextField taxPayerIdNumberField;

    @FXML
    private ComboBox<Job> jobComboBox;

    @FXML
    private ListView<Skill> skillListView;

    @FXML
    private Button backButton;

    private final CreateCollaboratorController collaboratorController;
    private final AuthenticationRepository authenticationRepository;

    public static final ArrayList<String> roles = new ArrayList<>(List.of(AuthenticationController.ROLE_HRM,AuthenticationController.ROLE_VFM,AuthenticationController.ROLE_QAM,AuthenticationController.ROLE_GSM));
    public static final ArrayList<String> docType = new ArrayList<>(List.of("CC", "Passport"));

    public CreateCollaboratorFXController() {
        this.authenticationRepository = new AuthenticationRepository();
        this.collaboratorController = new CreateCollaboratorController();
    }

    @FXML
    private void initialize() {
        // Populate ComboBoxes
        List<Job> jobs = collaboratorController.getAllJobs();
        jobComboBox.getItems().addAll(jobs);

        List<Skill> skills = collaboratorController.getAllSkills();
        //skillListView.getItems().addAll(skills);

        // Adicionar todas as habilidades ao ListView
        skillListView.setItems(FXCollections.observableArrayList(skills));

        // Permitir seleção múltipla
        skillListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        idDocTypeComboBox.getItems().addAll(docType);
    }

    @FXML
    private void handleCreateCollaborator() {
        try {
            String name = nameField.getText();
            LocalDate dateOfBirth = dateOfBirthPicker.getValue();
            LocalDate admissionDate = admissionDatePicker.getValue();
            String address = addressField.getText();
            int mobile = Integer.parseInt(mobileField.getText());
            String email = emailField.getText();
            String idDocType = idDocTypeComboBox.getValue();
            int docTypeNumber = Integer.parseInt(docTypeNumberField.getText());
            int taxPayerIdNumber = Integer.parseInt(taxPayerIdNumberField.getText());
            String password = "admin";
            String role = AuthenticationController.ROLE_Collaborator;
            Job job = jobComboBox.getValue();
            ArrayList<Skill> skills = new ArrayList<>(skillListView.itemsProperty().getValue());

            Collaborator collaborator = collaboratorController.addCollaborator(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role, skills);

            if (collaborator != null) {
                showAlert(Alert.AlertType.INFORMATION, "Collaborator Created", "Collaborator successfully created!");
                authenticationRepository.addUserWithRole(name,email,password,AuthenticationController.ROLE_Collaborator);
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Collaborator Not Created", "Collaborator not created!");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for mobile, doc type number, and tax payer ID number.");
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/collaborator/CollaboratorMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Collaborator Menu");
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

    private void clearFields() {
        nameField.clear();
        dateOfBirthPicker.setValue(null);
        admissionDatePicker.setValue(null);
        addressField.clear();
        mobileField.clear();
        emailField.clear();
        idDocTypeComboBox.setValue(null);
        docTypeNumberField.clear();
        taxPayerIdNumberField.clear();
        jobComboBox.setValue(null);
        //skillListView.setValue(null);
    }
}
