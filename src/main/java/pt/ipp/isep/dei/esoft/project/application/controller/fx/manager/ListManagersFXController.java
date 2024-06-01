package pt.ipp.isep.dei.esoft.project.application.controller.fx.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateManagerController;
import pt.ipp.isep.dei.esoft.project.domain.Manager;

import java.io.IOException;
import java.util.List;

public class ListManagersFXController {

    private final CreateManagerController managerController = new CreateManagerController();

    @FXML
    private TableView<Manager> managersTable;

    @FXML
    private TableColumn<Manager, String> nameColumn;

    @FXML
    private TableColumn<Manager, String> emailColumn;

    @FXML
    private TableColumn<Manager, String> departmentColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));

        managersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        nameColumn.setMinWidth(150);
        emailColumn.setMinWidth(200);
        departmentColumn.setMinWidth(300);

        listManagers();
    }

    private void listManagers() {
        List<Manager> managers = managerController.getAllManagers();
        managersTable.getItems().setAll(managers);
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
}