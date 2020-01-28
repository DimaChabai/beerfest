package by.beerfest.specification.impl;

import by.beerfest.constant.Query;
import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FestSpecificationUserFindByEmail extends FestSpecification {

    private String email;

    public FestSpecificationUserFindByEmail( String email) {
        this.email = email;
    }
    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(Query.FIND_USER_BY_EMAIL);
        statement.setString(1,email);
        return statement;
    }
}
