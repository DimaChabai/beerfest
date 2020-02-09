package by.beerfest.validator;

/**
 * Utility class for validate ticket information
 */
public class TicketValidator {

    private static final String NUMBER = "\\d+";

    public boolean validateTicketNumber(String num) {
        return num.matches(NUMBER) || num.isBlank();
    }
}
