package by.beerfest.specification.impl;

import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.beerfest.constant.Query.FIND_USER_BY_ID;

public class FestSpecificationUserFindById implements FestSpecification {

    private long id;

    public FestSpecificationUserFindById(long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID);
        statement.setLong(1, id);
        return statement;
    }
}
