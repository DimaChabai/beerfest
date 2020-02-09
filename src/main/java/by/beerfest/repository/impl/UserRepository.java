package by.beerfest.repository.impl;

import by.beerfest.entity.impl.User;
import by.beerfest.repository.Repository;
import by.beerfest.repository.RepositoryException;
import by.beerfest.service.impl.UserServiceImpl;
import by.beerfest.specification.FestSpecification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.beerfest.constant.Query.USER_INSERT;
import static by.beerfest.constant.Query.USER_UPDATE;

/**
 * Realization of {@code Repository} interface.
 * It is singleton.
 * Used to access the table 'user'.
 */
public class UserRepository extends Repository {

    private static UserRepository instance = new UserRepository();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return instance;
    }

    /**
     * Adds a user to the table 'user'.
     *
     * @param user object that contains information about user
     * @throws RepositoryException wraps SQLException
     */
    public int add(User user) throws RepositoryException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(USER_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getAvatar());
            statement.executeUpdate();
            int id;
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                resultSet.next();
                id = resultSet.getInt(1);
            }//todo
            return id;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    /**
     * Updates information about the user in the "user" table.
     *
     * @param user object that contains information about user
     * @throws RepositoryException wraps SQLException
     */
    public void update(User user) throws RepositoryException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(USER_UPDATE)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getAvatar());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    /**
     * Executes a query passed in a {@code FestSpecification} object
     *
     * @param specification object that contain {@code Statement} fo query
     * @return User {@code List}.
     * @throws RepositoryException if a database access error occurs;
     */
    public List<User> query(FestSpecification specification) throws RepositoryException {

        List<User> resultList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = specification.specified(connection);
             ResultSet resultSet = statement.executeQuery()) {
            User user;
            while (resultSet.next()) {
                user = new User();
                UserServiceImpl service = new UserServiceImpl();
                service.buildUser(resultSet, user);
                resultList.add(user);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return resultList;
    }

}
