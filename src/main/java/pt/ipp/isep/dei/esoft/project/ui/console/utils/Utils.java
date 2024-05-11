package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class Utils {

    static public String readLineFromConsole(String prompt) {
        try {
            System.out.print(prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public double readDoubleFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Date readDateFromConsole(String prompt) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println("  " + index + " - " + o.toString());
        }
        //System.out.println();
        System.out.println("  0 - Cancel");
    }

    static public Object selectsObject(List list) {
        String input;
        int value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list) {
        String input;
        int value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            try {
                value = Integer.valueOf(input);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    /**
     * Checks if a given number has a specified number of digits.
     *
     * @param number The number to check.
     * @param digits The number of digits to check for.
     * @return {@code true} if the number has the specified number of digits, {@code false} otherwise.
     * @throws IllegalArgumentException if the number is null or if the number of digits is negative.
     */
    public static boolean hasXDigits(Integer number, int digits) {
        if (number == null || digits < 0) {
            throw new IllegalArgumentException("Invalid input: number cannot be null and digits must be non-negative");
        }
        if (number == 0 && digits == 1) {
            return true; // Special case: 0 has 1 digit
        }
        if (number < 0) {
            number = Math.abs(number); // Consider only the positive value
        }
        return String.valueOf(number).length() == digits;
    }

    /**
     * Checks if a given string contains only letters and spaces.
     *
     * @param name The string to check.
     * @return {@code true} if the string contains only letters and spaces, {@code false} otherwise.
     */
    public static boolean isValidInput(String name) {
        // Allow only letters and spaces
        String regex = "^[a-zA-Z\\s]+$";
        // Check if the input matches the regular expression
        return name.matches(regex);
    }

    /**
     * Checks if a given string is a valid email address.
     *
     * @param email The string to check.
     * @return {@code true} if the string is a valid email address, {@code false} otherwise.
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false; // Empty or null string is not a valid email
        }

        // Check for presence of "@" and "."
        int atIndex = email.indexOf('@');
        if (atIndex < 0 || atIndex == 0 || atIndex == email.length() - 1) {
            return false; // "@" must be between characters, not at start/end
        }
        int dotIndex = email.lastIndexOf('.');
        if (dotIndex < atIndex || dotIndex == email.length() - 1) {
            return false; // "." must be after "@" and not at the end
        }

        // Basic check for username and domain length (adjust as needed)
        if (email.length() - atIndex - 1 < 2 || email.length() - dotIndex - 1 < 2) {
            return false; // Username and domain should each have at least 2 characters
        }

        // You can add further checks here for complex validation (e.g., regex)

        return true; // Passed basic checks, potentially valid
    }

    /**
     * Checks if a given date represents someone who is at least 18 years old.
     *
     * @param input The date to check.
     * @return {@code true} if the date represents someone who is at least 18 years old, {@code false} otherwise.
     */
    public static boolean isAtLeast18YearsOld(LocalDate input) {
        LocalDate today = LocalDate.now(); // Get the current date
        LocalDate eighteenYearsAgo = today.minusYears(18); // Calculate the date 18 years ago

        // Check if the input date is before the date 18 years ago
        return input.isBefore(eighteenYearsAgo);
    }

    /**
     * Checks if a mobile phone number is in the correct format.
     *
     * @param mobileNumber The mobile phone number to check.
     * @return {@code true} if the mobile phone number is in the correct format, {@code false} otherwise.
     */
    public static boolean isMobileNumberCorrect(String mobileNumber) {
        // Check if mobileNumber is null or empty
        if (mobileNumber == null || mobileNumber.isEmpty()) {
            return false;
        }

        // Check if mobileNumber matches the format: starts with '9' followed by 8 digits
        return mobileNumber.matches("^9[0-9]{8}$");
    }

    /**
     * Validates a Portuguese NIF (Número de Identificação Fiscal) number.
     *
     * @param number The NIF number to validate.
     * @return {@code true} if the NIF number is valid, {@code false} otherwise.
     */
    public static boolean isValidNIF(String number) {
        final int max = 9;

        // Check if the number is numeric and has 9 digits
        if (!number.matches("[0-9]+") || number.length() != max)
            return false;

        int checkSum = 0;

        // Calculate checkSum
        for (int i = 0; i < max - 1; i++) {
            checkSum += (number.charAt(i) - '0') * (max - i);
        }

        int checkDigit = 11 - (checkSum % 11);

        // If checkDigit is greater than 9, set it to zero
        if (checkDigit > 9)
            checkDigit = 0;

        // Compare checkDigit with the last digit of NIF
        return checkDigit == number.charAt(max - 1) - '0';
    }

    /**
     * Parses a date string and checks if it's in the correct format and represents a valid date.
     *
     * @param dateInput The date string to parse.
     * @return {@code true} if the date string is in the correct format and represents a valid date, {@code false} otherwise.
     */
    public static boolean parseDate(String dateInput) {
        // Regular expressions to check the date format
        Pattern pattern = Pattern.compile("^\\d{4}[-/]\\d{2}[-/]\\d{2}$");
        Matcher matcher = pattern.matcher(dateInput);

        if (matcher.matches()) {
            // Format the date correctly
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                // Parse the date
                LocalDate date = LocalDate.parse(dateInput.replace("/", "-"), formatter);
                // Check if the month and day are within correct limits
                int year = date.getYear();
                int month = date.getMonthValue();
                int day = date.getDayOfMonth();
                // Check the month
                if (month < 1 || month > 12) {
                    return false; // Invalid month
                }
                // Check the day
                if (day < 1 || day > date.lengthOfMonth()) {
                    return false; // Invalid day
                }
                // If it reaches here, the date is valid
                return true;
            } catch (Exception e) {
                // If there's any conversion error, the date is invalid
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Prompts the user for input and validates that the input contains only letters.
     *
     * @param prompt The prompt message to display to the user.
     * @param errorMessage The error message to display if the input is invalid.
     * @return The validated input containing only letters.
     */
    public static String requestOnlyLetters(String prompt, String errorMessage) {
        String input;
        do {
            input = Utils.readLineFromConsole(prompt); // Prompt user for input
            assert input != null; // Ensure input is not null
            if (!Utils.isValidInput(input)) {
                System.out.print(errorMessage); // Print error message if input is invalid
            }
        } while (!Utils.isValidInput(input)); // Continue prompting until valid input is provided
        return input; // Return the validated input containing only letters
    }

}