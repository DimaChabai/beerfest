package by.beerfest.service;

import by.beerfest.entity.impl.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserService {
    void buildUser(ResultSet resultSet, User user) throws SQLException;
}
