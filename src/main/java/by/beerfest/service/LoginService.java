package by.beerfest.service;

import by.beerfest.entity.User;

public interface LoginService {
    User login(String email, String password) throws ServiceException;
}
