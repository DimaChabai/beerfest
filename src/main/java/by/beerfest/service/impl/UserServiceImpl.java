package by.beerfest.service.impl;

import by.beerfest.entity.impl.User;
import by.beerfest.entity.UserRole;
import by.beerfest.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.beerfest.constant.ColumnName.*;
import static by.beerfest.constant.ColumnName.COL_AVATAR;

public class UserServiceImpl implements UserService {

    public void buildUser(ResultSet resultSet, User user) throws SQLException {
        user.setPassword(resultSet.getString(COL_PASSWORD));
        user.setPhoneNumber(resultSet.getString(COL_PHONE_NUMBER));
        user.setEmail(resultSet.getString(COL_EMAIL));
        user.setId(resultSet.getLong(COL_ID_USER));
        user.setRole(UserRole.valueOf(resultSet.getString(COL_ROLE_NAME)));
        user.setAvatar(resultSet.getString(COL_AVATAR));
    }
}
