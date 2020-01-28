package by.beerfest.specification.impl;

import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.beerfest.constant.Query.FIND_FREE_PLACE;

public class FestSpecificationPlaceFindAllFree extends FestSpecification {

    public FestSpecificationPlaceFindAllFree() {

    }

    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_FREE_PLACE);
    }
}