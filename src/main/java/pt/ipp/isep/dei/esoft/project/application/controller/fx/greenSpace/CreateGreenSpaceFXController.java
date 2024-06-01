package pt.ipp.isep.dei.esoft.project.application.controller.fx.greenSpace;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParsePosition;
import java.util.List;
import java.util.Locale;

import javafx.util.StringConverter;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateGreenSpacesController;
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

        List<Manager> managers = greenSpacesController.getAllManagers();
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
                System.out.println("Green Space successfully registered!");
                nameField.clear();
                areaField.clear();
                classificationChoiceBox.setValue(null);
                managerChoiceBox.setValue(null);
            } else {
                System.out.println("Green Space not registered!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid area value. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/greenSpace/GreenSpacesMenu.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Green Spaces Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}