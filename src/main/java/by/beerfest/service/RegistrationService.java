package by.beerfest.service;

public interface RegistrationService {
    boolean createAndAddUser(String email, String phoneNumber, String password) throws ServiceException;
}
