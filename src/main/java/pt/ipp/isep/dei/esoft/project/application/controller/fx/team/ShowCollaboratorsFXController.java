package pt.ipp.isep.dei.esoft.project.application.controller.fx.team;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Team;

public class ShowCollaboratorsFXController {

    @FXML
    private ListView<Collaborator> collaboratorsListView;

    private Team team;
    private boolean accepted = false;

    @FXML
    private void initialize() {
        collaboratorsListView.setCellFactory(lv -> new ListCell<Collaborator>() {
            @Override
            protected void updateItem(Collaborator collaborator, boolean empty) {
                super.updateItem(collaborator, empty);
                if (empty || collaborator == null) {
                    setText(null);
                } else {
                    setText("Nome:   " + collaborator.getName() +
                            "\nEmail:  " + collaborator.getEmail() +
                            "\nJob:    " + collaborator.getJob() +
                            "\nSkills: " + collaborator.getSkills());
                }
            }
        });
    }

    public void setTeam(Team team) {
        this.team = team;
        collaboratorsListView.setItems(FXCollections.observableArrayList(team.getCollaborators()));
    }

    @FXML
    private void handleAccept() {
        accepted = true;
        Stage stage = (Stage) collaboratorsListView.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleReject() {
        accepted = false;
        Stage stage = (Stage) collaboratorsListView.getScene().getWindow();
        stage.close();
    }

    public boolean isAccepted() {
        return accepted;
    }

    public boolean isRejected() {
        return !accepted;
    }
}