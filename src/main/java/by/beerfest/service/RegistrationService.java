package by.beerfest.service;

import by.beerfest.content.SessionRequestContent;

public interface RegistrationService {
    boolean addUser(String email, String phoneNumber, String password) throws ServiceException;
}
