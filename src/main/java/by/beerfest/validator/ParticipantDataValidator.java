package by.beerfest.validator;

public class ParticipantDataValidator {

    private static final String NAME_REGEX = ".+";

    public boolean nameValidate(String name){
        return name.matches(NAME_REGEX);
    }
}
