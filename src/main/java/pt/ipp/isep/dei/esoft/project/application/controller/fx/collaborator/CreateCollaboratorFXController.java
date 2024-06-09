package pt.ipp.isep.dei.esoft.project.application.controller.fx.collaborator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Job;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;

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

            String email = emailField.getText().toLowerCase();
            if (UtilsFX.checkEmail(email)) return;

            String idDocType = idDocTypeComboBox.getValue();
            int docTypeNumber = Integer.parseInt(docTypeNumberField.getText());

            int taxPayerIdNumber = Integer.parseInt(taxPayerIdNumberField.getText());
            if (UtilsFX.isValidNIF(String.valueOf(taxPayerIdNumber))) return;

            String password = "admin";
            String role = AuthenticationController.ROLE_Collaborator;
            Job job = jobComboBox.getValue();
            ArrayList<Skill> skills = new ArrayList<>(skillListView.getSelectionModel().getSelectedItems());

            Collaborator collaborator = collaboratorController.addCollaborator(name, dateOfBirth, admissionDate, address, mobile, email, idDocType, docTypeNumber, taxPayerIdNumber, job, password, role, skills);

            if (collaborator != null) {
                UtilsFX.showAlert(Alert.AlertType.INFORMATION, "Collaborator Created", "Collaborator successfully created!");
                authenticationRepository.addUserWithRole(name,email,password,AuthenticationController.ROLE_Collaborator);
                clearFields();
            } else {
                UtilsFX.showAlert(Alert.AlertType.ERROR, "Collaborator Not Created", "Collaborator not created!");
            }
        } catch (NumberFormatException e) {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter valid numbers for mobile, doc type number, and tax payer ID number.");
        }
    }


    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/collaborator/CollaboratorMenu.fxml", backButton, "Collaborator Menu");
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
        skillListView.getSelectionModel().clearSelection();
    }
}
