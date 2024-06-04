package pt.ipp.isep.dei.esoft.project.application.controller.fx.collaborator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateCollaboratorController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;

import java.util.List;

public class ListCollaboratorFXController {

    private final CreateCollaboratorController collaboratorController = new CreateCollaboratorController();

    @FXML
    private TableView<Collaborator> collaboratorsTable;

    @FXML
    private TableColumn<Collaborator, String> nameColumn;

    @FXML
    private TableColumn<Collaborator, String> emailColumn;

    @FXML
    private TableColumn<Collaborator, String> jobColumn;

    @FXML
    private TableColumn<Collaborator, String> skillColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        jobColumn.setCellValueFactory(new PropertyValueFactory<>("job"));
        skillColumn.setCellValueFactory(new PropertyValueFactory<>("skills"));

        collaboratorsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        nameColumn.setMinWidth(150);
        emailColumn.setMinWidth(200);
        jobColumn.setMinWidth(150);
        skillColumn.setMinWidth(300);

        listCollaborators();
    }

    private void listCollaborators() {
        List<Collaborator> collaborators = collaboratorController.getAllCollaborators();
        collaboratorsTable.getItems().setAll(collaborators);
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/collaborator/CollaboratorMenu.fxml", backButton, "Collaborator Menu");
    }
}