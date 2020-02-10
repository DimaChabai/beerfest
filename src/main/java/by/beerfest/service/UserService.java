package by.beerfest.service;

import by.beerfest.entity.impl.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserService {

    void buildUser(ResultSet resultSet, User user) throws SQLException;

    User authenticate(String email, String password) throws ServiceException;

    boolean createAndAddUser(String email, String phoneNumber, String password) throws ServiceException;

}
