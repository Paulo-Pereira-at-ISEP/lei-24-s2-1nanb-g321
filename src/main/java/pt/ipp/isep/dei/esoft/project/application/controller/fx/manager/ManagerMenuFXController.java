package pt.ipp.isep.dei.esoft.project.application.controller.fx.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

public class ManagerMenuFXController {

    @FXML
    private Button createManagerButton;

    @FXML
    private Button listManagersButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        createManagerButton.setOnAction(event -> handleCreateManager());
        listManagersButton.setOnAction(event -> handleListManagers());
        backButton.setOnAction(event -> handleBack());
    }

    private void handleCreateManager() {
        UtilsFX.bottonControl("/fxml/manager/CreateManager.fxml", createManagerButton, "Create Manager");
    }

    private void handleListManagers() {
        UtilsFX.bottonControl("/fxml/manager/ListManagers.fxml", listManagersButton, "List Managers");
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/employee/HRMMenu.fxml", backButton, "HRM Menu");
    }
}
