package by.beerfest.specification.impl;

import by.beerfest.constant.Query;
import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.beerfest.constant.Query.FIND_PLACE_BY_ID;

public class FestSpecificationPlaceFindById extends FestSpecification {
    private long id;

    public FestSpecificationPlaceFindById(long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_PLACE_BY_ID);
        statement.setLong(1,id);
        return statement;
    }
}
