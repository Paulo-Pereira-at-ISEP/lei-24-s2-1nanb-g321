package pt.ipp.isep.dei.esoft.project.application.controller.fx.skill;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateSkillController;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.List;

public class ListSkillsFXController {

    private final CreateSkillController skillController = new CreateSkillController();

    @FXML
    private TableView<Skill> skillsTable;

    @FXML
    private TableColumn<Skill, String> nameColumn;

    @FXML
    private TableColumn<Skill, String> descriptionColumn;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        skillsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        nameColumn.setMinWidth(150);
        descriptionColumn.setMinWidth(300);

        listSkills();
    }

    private void listSkills() {
        List<Skill> skills = skillController.getAllSkills();
        skillsTable.getItems().setAll(skills);
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/skill/SkillMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Skill Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}