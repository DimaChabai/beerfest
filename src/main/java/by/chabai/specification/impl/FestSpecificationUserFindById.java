package by.chabai.specification.impl;

import by.chabai.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.chabai.constant.Query.FIND_USER_BY_ID;

public class FestSpecificationUserFindById extends FestSpecification {

    private long id;

    public FestSpecificationUserFindById( long id) {
        this.id = id;
    }
    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID);
        statement.setLong(1,id);
        return statement;
    }
}
