package pt.ipp.isep.dei.esoft.project.application.controller.fx.authorization;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class LoginFXController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    private final AuthenticationController ctrl;

    public LoginFXController() {
        this.ctrl = new AuthenticationController();
    }

    @FXML
    private void handleLogin() {
        String userId = emailField.getText();
        String password = passwordField.getText();

        boolean success = ctrl.doLogin(userId, password);

        if (success) {
            List<UserRoleDTO> roles = ctrl.getUserRoles();
            if (roles == null || roles.isEmpty()) {
                System.out.println("No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    redirectToRoleUI(role);
                } else {
                    System.out.println("No role selected.");
                }
            }
        } else {
            System.out.println("Invalid UserId and/or Password.");
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
            } else {
                System.out.println("There is no UI for users with role '" + role.getDescription() + "'");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}