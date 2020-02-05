package by.beerfest.validator;

public class UserDataValidator {

    private static final String EMAIL_REGEX = "[^@]+@[^@]+";
    private static final String PASSWORD_REGEX = ".+";
    private static final String PHONE_NUMBER_REGEX = "\\+375((29)|(44)|(33)|(25)) \\d{7}";

    public boolean emailValidate(String email){
        return email.matches(EMAIL_REGEX);
    }

    public boolean passwordValidate(String password){
        return password.matches(PASSWORD_REGEX);//@FIXME
    }

    public boolean phoneNumberValidate(String phoneNumber){
        return phoneNumber.matches(PHONE_NUMBER_REGEX);
    }
}
