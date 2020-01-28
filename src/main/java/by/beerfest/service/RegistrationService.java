package by.beerfest.service;

import by.beerfest.content.SessionRequestContent;

public interface RegistrationService {
    boolean createAndAddUser(String email, String phoneNumber, String password) throws ServiceException;
}
