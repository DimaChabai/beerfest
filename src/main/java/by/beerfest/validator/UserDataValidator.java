package by.beerfest.validator;

public class UserDataValidator {

    public boolean emailValidate(String email){
        return email.matches("[^@]+@[^@]+");
    }

    public boolean passwordValidate(String password){
        return password.matches(".");//@FIXME
    }

    public boolean phoneNumberValidate(String phoneNumber){
        return phoneNumber.matches("\\+375((29)|(44)|(33)|(25)) \\d{7}");
    }
}
