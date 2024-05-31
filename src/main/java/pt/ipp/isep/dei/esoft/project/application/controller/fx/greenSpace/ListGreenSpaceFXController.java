package pt.ipp.isep.dei.esoft.project.application.controller.fx.greenSpace;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;

import java.io.IOException;
import java.util.List;

public class ListGreenSpaceFXController {

    private final CreateGreenSpacesController greenSpacesController = new CreateGreenSpacesController();

    @FXML
    private TableView<GreenSpace> greenSpacesTable;

    @FXML
    private TableColumn<GreenSpace, String> nameColumn;

    @FXML
    private TableColumn<GreenSpace, String> areaColumn;

    @FXML
    private TableColumn<GreenSpace, String> classificationColumn;

    @FXML
    private TableColumn<GreenSpace, String> managerColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
        classificationColumn.setCellValueFactory(new PropertyValueFactory<>("classification"));

        // Custom CellValueFactory for managerColumn to show manager's name
        managerColumn.setCellValueFactory(cellData -> {
            GreenSpace greenSpace = cellData.getValue();
            String managerName = (greenSpace.getManager() != null) ? greenSpace.getManager().getName() : "N/A";
            return new SimpleStringProperty(managerName);
        });

        greenSpacesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        nameColumn.setMinWidth(150);
        areaColumn.setMinWidth(75);
        classificationColumn.setMinWidth(150);
        managerColumn.setMinWidth(150); // Adjusted to a reasonable width

        listGreenSpaces();
    }

    private void listGreenSpaces() {
        List<GreenSpace> greenSpaces = greenSpacesController.getAllGreenSpaces();
        greenSpacesTable.getItems().setAll(greenSpaces);
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/greenSpace/GreenSpacesMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Green Space Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}