package pt.ipp.isep.dei.esoft.project.application.controller.fx.greenSpace;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParsePosition;
import java.util.List;
import java.util.Locale;

import javafx.util.StringConverter;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateGreenSpacesController;
import pt.ipp.isep.dei.esoft.project.application.controller.fx.utils.UtilsFX;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.Manager;


public class CreateGreenSpaceFXController {

    private final CreateGreenSpacesController greenSpacesController = new CreateGreenSpacesController();

    @FXML
    private TextField nameField;

    @FXML
    private TextField areaField;

    @FXML
    private ChoiceBox<String> classificationChoiceBox;

    @FXML
    private ChoiceBox<Manager> managerChoiceBox;

    @FXML
    private Button submitButton;

    @FXML
    private Button backButton;

    @FXML
    private void initialize() {
        classificationChoiceBox.getItems().addAll("Garden", "Medium-Sized Park", "Large-Sized Park");

        List<Manager> managers = greenSpacesController.getManagerWithRoleGSM();
        managerChoiceBox.setItems(FXCollections.observableArrayList(managers));

        managerChoiceBox.setConverter(new StringConverter<Manager>() {
            @Override
            public String toString(Manager manager) {
                return manager != null ? manager.getName() : "";
            }

            @Override
            public Manager fromString(String string) {
                return managerChoiceBox.getItems().stream().filter(manager -> manager.getName().equals(string)).findFirst().orElse(null);
            }
        });

        // Add a TextFormatter to the areaField to restrict input to valid double values
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("#.0#", symbols);

        TextFormatter<Double> textFormatter = new TextFormatter<>(c -> {
            if (c.getControlNewText().isEmpty()) {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition(0);
            Object object = format.parse(c.getControlNewText(), parsePosition);

            if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
                return null;
            } else {
                return c;
            }
        });

        areaField.setTextFormatter(textFormatter);

        submitButton.setOnAction(event -> submitData());
        backButton.setOnAction(event -> handleBack());
    }

    private void submitData() {
        try {
            String name = nameField.getText();
            double area = Double.parseDouble(areaField.getText());
            String classification = classificationChoiceBox.getValue();
            Manager manager = managerChoiceBox.getValue();

            if (name.isEmpty() || classification == null || manager == null) {
                throw new IllegalArgumentException("All fields must be filled.");
            }

            GreenSpace greenSpace = greenSpacesController.createGreenSpace(name, area, classification, manager);

            if (greenSpace != null) {
                UtilsFX.showAlert(Alert.AlertType.INFORMATION, "Green Space created","Green Space successfully registered!");
                clearFields();
            } else {
                UtilsFX.showAlert(Alert.AlertType.ERROR, "Green Space Not Created","Green Space not registered!");
            }
        } catch (NumberFormatException e) {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Green Space Not Created","Invalid area value. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Green Space Not Created",e.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        UtilsFX.bottonControl("/fxml/greenSpace/GreenSpacesMenu.fxml", backButton, "Green Spaces Menu");
    }

    private void clearFields(){
        nameField.clear();
        areaField.clear();
        classificationChoiceBox.setValue(null);
        managerChoiceBox.setValue(null);
    }
}