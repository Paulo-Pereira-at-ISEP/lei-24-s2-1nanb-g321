package pt.ipp.isep.dei.esoft.project.application.controller.fx.greenSpace;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.IOException;

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/greenSpace/CreateGreenSpace.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) createGreenSpaceButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Create Green Space");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleListAllGreenSpaces() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/greenSpace/ListAllGreenSpaces.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) listAllGreenSpaceButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("List All Green Spaces");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleListGreenSpaces() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/greenSpace/ListGreenSpaces.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) listGreenSpaceButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("List Green Spaces Managed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/employee/GSMMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("GSM Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
