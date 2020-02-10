package by.beerfest.validator;

/**
 * Utility class for validate user information
 */
public class UserDataValidator {

    private static final String EMAIL_REGEX = "[^@]+@[^@]+";
    private static final String AVATAR_REGEX = ".+\\.(jpg|png)";
    private static final String PHONE_NUMBER_REGEX = "\\+375((29)|(44)|(33)|(25)) ?\\d{7}";
    private static final String PASSWORD_REGEX = ".{7,}";

    public boolean emailValidate(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public boolean passwordValidate(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public boolean phoneNumberValidate(String phoneNumber) {
        return phoneNumber.matches(PHONE_NUMBER_REGEX);
    }

    public boolean avatarValidate(String path) {
        return path.matches(AVATAR_REGEX);
    }
}
