package by.beerfest.service;

import by.beerfest.entity.impl.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Interface for working with 'user' table.
 * Provides a methods for creating and authentication a user.
 */
public interface UserService {

    void buildUser(ResultSet resultSet, User user) throws SQLException;

    Optional<User> authenticate(String email, String password) throws ServiceException;

    boolean createAndAddUser(String email, String phoneNumber, String password) throws ServiceException;

    void sendMessageOnEmail(String email, String messageKey) throws ServiceException;
}
