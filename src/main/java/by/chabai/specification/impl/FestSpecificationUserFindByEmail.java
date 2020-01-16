package by.chabai.specification.impl;

import by.chabai.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.chabai.constant.Query.FIND_USER_BY_EMAIL;

public class FestSpecificationUserFindByEmail extends FestSpecification {

    private String email;

    public FestSpecificationUserFindByEmail(Connection connection, String email) {
        super(connection);
        this.email = email;
    }
//@TODO
    @Override
    public PreparedStatement specified() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL);
        statement.setString(1,email);
        return statement;
    }
}
