package by.beerfest.specification.impl;

import by.beerfest.constant.Query;
import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FestSpecificationPlaceFindById extends FestSpecification {
    private long id;

    public FestSpecificationPlaceFindById(long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(Query.FIND_PLACE_BY_ID);
        statement.setLong(1,id);
        return statement;
    }
}
