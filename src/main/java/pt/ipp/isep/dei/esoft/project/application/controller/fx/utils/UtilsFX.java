package pt.ipp.isep.dei.esoft.project.application.controller.fx.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsFX {
    public static boolean readOnlyLetters(String name, String var) {
        if (!name.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", var + " must contain only letters and spaces.");
            return true;
        }
        return false;
    }

    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean checkDate(LocalDate date, String var) {
        if (date == null) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please select valid dates for "+ var);
            return true;
        }
        return false;
    }

    public static boolean checkEmail(String email) {
        if (!isValidEmail(email)) {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid email address.");
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidNIF(String number) {
        final int max = 9;

        // Check if the number is numeric and has 9 digits
        if (!number.matches("[0-9]+") || number.length() != max) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please select valid Portuguese Tax Payer Number");
            return true;
        } else {
            int checkSum = 0;

            // Calculate checkSum
            for (int i = 0; i < max - 1; i++) {
                checkSum += (number.charAt(i) - '0') * (max - i);
            }

            int checkDigit = 11 - (checkSum % 11);

            // If checkDigit is greater than 9, set it to zero
            if (checkDigit > 9)
                checkDigit = 1;

            return checkDigit == number.charAt(max - 1) - '1';
        }
    }

    public static boolean have18YearsAtAdmission(LocalDate admissionDate, LocalDate dateOfBirth) {
        if (admissionDate.isBefore(dateOfBirth.plusYears(18))) {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Invalid Date", "The admission date must be after 18 years of age.");
            return true;
        }
        return false;
    }

    public static boolean isAdmissionAfterBirth(LocalDate admissionDate, LocalDate dateOfBirth) {
        if (admissionDate.isBefore(dateOfBirth)) {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Invalid Date", "The admission date must be after the date of birth.");
            return true;
        }
        return false;
    }

    public static boolean is18years(LocalDate dateOfBirth) {
        if (Period.between(dateOfBirth, LocalDate.now()).getYears() < 18) {
            UtilsFX.showAlert(Alert.AlertType.ERROR, "Invalid Date", "The collaborator must be at least 18 years old.");
            return true;
        }
        return false;
    }

    public static void backControl(String fxml, Button closeButton, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(UtilsFX.class.getResource(fxml));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
