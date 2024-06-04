package pt.ipp.isep.dei.esoft.project.application.controller.fx.greenSpace;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import java.util.List;

public class ListGreenSpaceFXController {

    private final CreateGreenSpacesController greenSpacesController = new CreateGreenSpacesController();

    @FXML
    private TableView<GreenSpace> greenSpacesTable;

    @FXML
    private TableColumn<GreenSpace, String> nameColumn;

    @FXML
    private TableColumn<GreenSpace, Double> areaColumn;

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
        List<GreenSpace> greenSpaces = greenSpacesController.getGreenSpacesManaged(ApplicationSession.getInstance().getCurrentSession().getUserEmail());
        greenSpacesTable.getItems().setAll(greenSpaces);
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/greenSpace/GreenSpacesMenu.fxml", backButton, "Green Space Menu");
    }
}