package by.chabai.repository;

import by.chabai.entity.User;
import by.chabai.entity.UserRole;
import by.chabai.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.chabai.constant.Query.*;

public class UserRepository extends Repository {

    private static UserRepository instance = new UserRepository();

    public static UserRepository getInstance() {
        return instance;
    }

    private UserRepository() {
    }


    public void add(User user) {
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(USER_INSERT);
            //@TODO правильно ли вынес запрос
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getAvatar());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectionPool.releaseConnection(conn);
        }
    }

    public void update(User user) {
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(USER_UPDATE);
            //@TODO Как вынести
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getRole().toString());
            statement.setString(5, user.getAvatar());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(conn);
        }
    }

    public List<User> query(FestSpecification specification) {
        ResultSet resultSet = null;
        List<User> resultList = new ArrayList<>();
        try {
            resultSet = specification.specified().executeQuery();
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
            e.printStackTrace();
        }
        return resultList;
    }

}
