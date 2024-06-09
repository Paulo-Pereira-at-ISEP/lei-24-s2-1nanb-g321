package pt.ipp.isep.dei.esoft.project.application.controller.fx.authorization;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class LoginFXController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button closeButton;

    private final AuthenticationController ctrl;

    public String email;

    public LoginFXController() {
        this.ctrl = new AuthenticationController();
    }

    @FXML
    private void initialize() {
        closeButton.setOnAction(event -> handleClose());
    }

    @FXML
    private void handleClose() {
        UtilsFX.closeControl(closeButton);
    }

    @FXML
    private void handleLogin() {
        email = emailField.getText();
        String password = passwordField.getText();

        boolean success = ctrl.doLogin(email, password);

        if (success) {
            List<UserRoleDTO> roles = ctrl.getUserRoles();
            if (roles == null || roles.isEmpty()) {
                UtilsFX.showAlert(Alert.AlertType.INFORMATION, "No Role Assigned", "No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    redirectToRoleUI(role);
                } else {
                    UtilsFX.showAlert(Alert.AlertType.INFORMATION, "No Role Selected", "No role selected.");
                }
            }
        } else {
            UtilsFX.showAlert(Alert.AlertType.INFORMATION, "Invalid Login", "Invalid Email and/or Password!");
        }
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return null;
        }
    }

    private void redirectToRoleUI(UserRoleDTO role) {
        try {
            if (role.getDescription().equals(AuthenticationController.ROLE_HRM)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/HRMMenu.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("HRM Menu");
            } else if (role.getDescription().equals(AuthenticationController.ROLE_GSM)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/GSMMenu.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("GSM Menu");
            } else if (role.getDescription().equals(AuthenticationController.ROLE_Collaborator)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/CollaboratorMenu.fxml"));
                Scene scene = new Scene(loader.load());
                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Collaborator Menu");

            } else {
                System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}