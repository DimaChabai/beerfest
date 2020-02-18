package by.beerfest.specification.impl;

import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.beerfest.specification.Query.FIND_USER_BY_EMAIL;

public class FestSpecificationUserFindByEmail implements FestSpecification {

    private String email;

    public FestSpecificationUserFindByEmail(String email) {
        this.email = email;
    }

    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL);
        statement.setString(1, email);
        return statement;
    }
}
