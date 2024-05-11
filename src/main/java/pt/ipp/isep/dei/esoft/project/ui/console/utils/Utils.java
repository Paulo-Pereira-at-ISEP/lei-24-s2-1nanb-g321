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
    /*
    public static boolean hasXDigits(String input, int numero) {
        if (input == null || input.isEmpty()) {
            return false; // Handle empty input
        }
        return input.length() == numero && input.matches("[0-" +numero+ "]+"); // Check only digits
    }
*/
    public static boolean isValidInput(String name) {
        // Allow only letters and spaces
        String regex = "^[a-zA-Z\\s]+$";
        // Check if the input matches the regular expression
        return name.matches(regex);
    }

    public static boolean hasAtSymbol(String input) {
        if (input == null || input.isEmpty()) {
            return false; // Empty or null string doesn't contain "@"
        }
        return input.indexOf('@') != -1;
    }

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

    public static boolean isAtLeast18YearsOld (LocalDate input) {

        LocalDate today = LocalDate.now(); // Get current date
        LocalDate eighteenYearsAgo = today.minusYears(18); // Calculate date 18 years ago

        return input.isBefore(eighteenYearsAgo);
    }

    public static boolean isMobileNumberCorrect (String mobileNumber) {
        if (mobileNumber == null || mobileNumber.isEmpty()) {
            return false;
        }
        return mobileNumber.matches("^9[0-9]{8}$");
    }

    public static boolean isValidNIF(String number) {
        final int max=9;
        //check if is numeric and has 9 numbers
        if (!number.matches("[0-9]+") || number.length()!=max) return false;
        int checkSum=0;
        //calculate checkSum
        for (int i=0; i<max-1; i++){
            checkSum+=(number.charAt(i)-'0')*(max-i);
        }
        int checkDigit=11-(checkSum % 11);
        //if checkDigit is higher than 9 set it to zero
        if (checkDigit>9) checkDigit=0;
        //compare checkDigit with the last number of NIF
        return checkDigit==number.charAt(max-1)-'0';
    }

    public static boolean parseDate(String dateInput) {
        // Expressões regulares para verificar o formato da data
        Pattern pattern = Pattern.compile("^\\d{4}[-/]\\d{2}[-/]\\d{2}$");
        Matcher matcher = pattern.matcher(dateInput);

        if (matcher.matches()) {
            // Formatar a data corretamente
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                // Parse da data
                LocalDate date = LocalDate.parse(dateInput.replace("/", "-"), formatter);
                // Verificar se o mês e o dia estão dentro dos limites corretos
                int year = date.getYear();
                int month = date.getMonthValue();
                int day = date.getDayOfMonth();
                // Verificar o mês
                if (month < 1 || month > 12) {
                    return false; // Mês inválido
                }
                // Verificar o dia
                if (day < 1 || day > date.lengthOfMonth()) {
                    return false; // Dia inválido
                }
                // Se chegou até aqui, a data é válida
                return true;
            } catch (Exception e) {
                // Se houver algum erro na conversão, a data é inválida
                return false;
            }
        } else {
            return false;
        }
    }
}