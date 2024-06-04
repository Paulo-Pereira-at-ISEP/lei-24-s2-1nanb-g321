package pt.ipp.isep.dei.esoft.project.application.controller.fx.manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateManagerController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Manager;
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
        UtilsFX.bottonControl("/fxml/manager/ManagerMenu.fxml", backButton, "Manager Menu");
    }
}