package pt.ipp.isep.dei.esoft.project.application.controller.fx.manager;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateManagerController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Manager;
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

    @FXML
    private Button backButton;

    private final CreateManagerController managerController;

    public static final ArrayList<String> roles = new ArrayList<>(List.of(AuthenticationController.ROLE_HRM,AuthenticationController.ROLE_VFM,AuthenticationController.ROLE_QAM,AuthenticationController.ROLE_GSM));
    public static final ArrayList<String> docType = new ArrayList<>(List.of("CC", "Passport"));

    public CreateManagerFXController() {
        this.managerController = new CreateManagerController();
    }

    @FXML
    private void initialize() {
        idDocTypeComboBox.getItems().addAll(docType);
        roleComboBox.getItems().addAll(roles);
    }

    @FXML
    private void handleCreateManager() {
        try {
            String name = nameField.getText();
            if (UtilsFX.readOnlyLetters(name, "Name")) return;

            LocalDate dateOfBirth = dateOfBirthPicker.getValue();
            if (UtilsFX.checkDate(dateOfBirth, "Date of Birth")) return;
            if (UtilsFX.is18years(dateOfBirth)) return;

            LocalDate admissionDate = admissionDatePicker.getValue();
            if (UtilsFX.checkDate(admissionDate, "Admission Date")) return;
            if (UtilsFX.isAdmissionAfterBirth(admissionDate, dateOfBirth)) return;
            if (UtilsFX.have18YearsAtAdmission(admissionDate, dateOfBirth)) return;

            String address = addressField.getText();
            int mobile = Integer.parseInt(mobileField.getText());

            String email = emailField.getText();
            if (UtilsFX.checkEmail(email)) return;

            String idDocType = idDocTypeComboBox.getValue();
            int docTypeNumber = Integer.parseInt(docTypeNumberField.getText());
            int taxPayerIdNumber = Integer.parseInt(taxPayerIdNumberField.getText());
            if (UtilsFX.isValidNIF(String.valueOf(taxPayerIdNumber))) return;

            String password = "admin";
            String role = roleComboBox.getValue();
            Job job = getJob(role);

            Manager manager = managerController.addManager(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role, role);

            if (manager != null) {
                UtilsFX.showAlert(Alert.AlertType.INFORMATION, "Manager Created", "Manager successfully created!");
                clearFields();
            } else {
                UtilsFX.showAlert(Alert.AlertType.ERROR, "Manager Not Created", "Manager not created!");
            }
        } catch (NumberFormatException e) {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for mobile, doc type number, and tax payer ID number.");
        }
    }

    private Job getJob(String role) {
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
        return job;
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/manager/ManagerMenu.fxml", backButton, "Manager Menu");
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
        roleComboBox.setValue(null);
    }
}
