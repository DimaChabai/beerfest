package by.chabai.repository;

import by.chabai.entity.User;
import by.chabai.entity.UserRole;
import by.chabai.service.StatementService;
import by.chabai.specification.FestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.chabai.constant.ColumnName.*;
import static by.chabai.constant.Query.USER_INSERT;
import static by.chabai.constant.Query.USER_UPDATE;

public class UserRepository extends Repository {

    private static Logger logger = LogManager.getLogger();
    private static UserRepository instance = new UserRepository();

    public static UserRepository getInstance() {
        return instance;
    }

    private UserRepository() {
    }


    public void add(User user) {

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(USER_INSERT)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getAvatar());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    public void update(User user) {

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(USER_UPDATE)) {
            //@TODO Надо ли это в сервис
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getAvatar());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
           logger.warn(e.getMessage());
        }
    }

    public List<User> query(FestSpecification specification) {
        ResultSet resultSet = null;
        List<User> resultList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            resultSet = specification.specified(connection).executeQuery();
            User user;
            while (resultSet.next()) {
                user = new User();
                user.setPassword(resultSet.getString(COL_PASSWORD));
                user.setPhoneNumber(resultSet.getString(COL_PHONE_NUMBER));
                user.setEmail(resultSet.getString(COL_EMAIL));
                user.setId(resultSet.getLong(COL_ID_USER));
                user.setRole(UserRole.valueOf(resultSet.getString(COL_ROLE_NAME)));
                user.setAvatar(resultSet.getString(COL_AVATAR));
                resultList.add(user);
            }
        } catch (SQLException e) {
           logger.warn(e.getMessage());
        }
        return resultList;
    }

}
