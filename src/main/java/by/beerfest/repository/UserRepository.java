package by.beerfest.repository;

import by.beerfest.constant.Query;
import by.beerfest.entity.User;
import by.beerfest.service.impl.UserServiceImpl;
import by.beerfest.specification.FestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Repository {

    private static Logger logger = LogManager.getLogger();
    private static UserRepository instance = new UserRepository();

    public static UserRepository getInstance() {
        return instance;
    }

    private UserRepository() {
    }

    public void add(User user) throws RepositoryException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(Query.USER_INSERT)) {

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getAvatar());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new RepositoryException(e);
        }
    }

    public void update(User user) throws RepositoryException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(Query.USER_UPDATE)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getAvatar());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
           logger.error(e);
           throw new RepositoryException(e);
        }
    }

    public List<User> query(FestSpecification specification) throws RepositoryException {
        ResultSet resultSet = null;
        List<User> resultList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            resultSet = specification.specified(connection).executeQuery();
            User user;
            while (resultSet.next()) {
                user = new User();
                UserServiceImpl service = new UserServiceImpl();
                service.buildUser(resultSet,user);//@TODO сервис
                resultList.add(user);
            }
        } catch (SQLException e) {
           logger.error(e);
           throw new RepositoryException(e);
        }
        return resultList;
    }

}
