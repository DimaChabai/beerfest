package by.beerfest.validator;

public class ParticipantDataValidator {

    public boolean nameValidate(String name){
        return name.matches(".+");
    }
}
