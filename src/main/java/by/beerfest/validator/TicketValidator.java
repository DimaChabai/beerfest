package by.beerfest.validator;

public class TicketValidator {

    private static final String NUMBER = "\\d+";

    public boolean validate(String num){
        return num.matches(NUMBER) || num.isBlank();
    }
}
