package pt.ipp.isep.dei.esoft.project.application.controller.fx.greenSpace;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;

public class GreenSpacesMenuFXController {

    @FXML
    private Button createGreenSpaceButton;

    @FXML
    private Button listAllGreenSpaceButton;

    @FXML
    private Button listGreenSpaceButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        createGreenSpaceButton.setOnAction(event -> handleCreateGreenSpace());
        listAllGreenSpaceButton.setOnAction(event -> handleListAllGreenSpaces());
        listGreenSpaceButton.setOnAction(event -> handleListGreenSpaces());
        backButton.setOnAction(event -> handleBack());
    }

    private void handleCreateGreenSpace() {
        UtilsFX.bottonControl("/fxml/greenSpace/CreateGreenSpace.fxml", createGreenSpaceButton, "Create Green Space");
    }

    private void handleListAllGreenSpaces() {
        UtilsFX.bottonControl("/fxml/greenSpace/ListAllGreenSpaces.fxml", listAllGreenSpaceButton, "List All Green Spaces");
    }

    private void handleListGreenSpaces() {
        UtilsFX.bottonControl("/fxml/greenSpace/ListGreenSpaces.fxml", listGreenSpaceButton, "List Green Spaces");
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/employee/GSMMenu.fxml", backButton, "GSM Menu");
    }
}
