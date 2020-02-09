package by.beerfest.validator;

/**
 * Utility class for validate participant information
 */
public class ParticipantDataValidator {

    private static final String NAME_REGEX = ".{10,}";

    public boolean companyNameValidate(String companyName) {
        return companyName.matches(NAME_REGEX);
    }
}
