package pt.ipp.isep.dei.esoft.project.application.controller.fx.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateManagerController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Manager;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateManagerFXController {

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
    private ComboBox<String> roleComboBox;
/*
    @FXML
    private ComboBox<Job> jobComboBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField departmentField;
*/
    @FXML
    private Button backButton;

    private final CreateManagerController managerController;
    private final AuthenticationRepository authenticationRepository;

    public static final ArrayList<String> roles = new ArrayList<>(List.of(AuthenticationController.ROLE_HRM,AuthenticationController.ROLE_VFM,AuthenticationController.ROLE_QAM,AuthenticationController.ROLE_GSM));
    public static final ArrayList<String> docType = new ArrayList<>(List.of("CC", "Passport"));

    public CreateManagerFXController() {
        this.authenticationRepository = new AuthenticationRepository();
        this.managerController = new CreateManagerController();
    }

    @FXML
    private void initialize() {
        // Populate ComboBoxes
        //List<Job> jobs = managerController.getAllJobs();
        //jobComboBox.getItems().addAll(jobs);

        idDocTypeComboBox.getItems().addAll(docType);
        roleComboBox.getItems().addAll(roles);

        // Set default values
        //passwordField.setText("admin");
        //departmentField.setText("HRM");
    }

    @FXML
    private void handleCreateManager() {
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
            String role = roleComboBox.getValue();
            //String department = role;
            Job job;

            if(Objects.equals(role, AuthenticationController.ROLE_HRM)){
                job = new Job("Manager HR", "Human Resources Manager");
            } else if(Objects.equals(roleComboBox.getValue(), AuthenticationController.ROLE_VFM)){
                job = new Job("Manager VF", "Vehicle and Equipment Fleet Manager");
            } else if(Objects.equals(roleComboBox.getValue(), AuthenticationController.ROLE_QAM)){
                job = new Job("Manager QAM", "Software Quality Assessment Team Manager");
            } else if(Objects.equals(roleComboBox.getValue(), AuthenticationController.ROLE_GSM)){
                job = new Job("Manager GSM", "Green Spaces Manager");
            } else {
                job = new Job("Manager","Manager");
            }

            Manager manager = managerController.addManager(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role, role);

            if (manager != null) {
                showAlert(Alert.AlertType.INFORMATION, "Manager Created", "Manager successfully created!");
                if (role.equals(AuthenticationController.ROLE_HRM)) {
                    authenticationRepository.addUserWithRole(name,email,password,
                            AuthenticationController.ROLE_HRM);
                }
                else if (role.equals(AuthenticationController.ROLE_VFM)) {
                    authenticationRepository.addUserWithRole(name,email,password,
                            AuthenticationController.ROLE_VFM);
                }
                else if (role.equals(AuthenticationController.ROLE_QAM)) {
                    authenticationRepository.addUserWithRole(name,email,password,
                            AuthenticationController.ROLE_QAM);
                }
                else if (role.equals(AuthenticationController.ROLE_GSM)) {
                    authenticationRepository.addUserWithRole(name,email,password,
                            AuthenticationController.ROLE_GSM);
                }
                else {
                    authenticationRepository.addUserWithRole(name,email,password,
                            AuthenticationController.ROLE_Collaborator);
                }
                clearFields();
            } else {
                showAlert(Alert.AlertType.ERROR, "Manager Not Created", "Manager not created!");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for mobile, doc type number, and tax payer ID number.");
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/manager/ManagerMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Manager Menu");
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
        //jobComboBox.setValue(null);
        roleComboBox.setValue(null);
        //passwordField.clear();
        //departmentField.clear();
    }
}
