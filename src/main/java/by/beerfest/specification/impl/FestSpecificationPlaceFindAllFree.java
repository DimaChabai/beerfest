package by.beerfest.specification.impl;

import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.beerfest.specification.Query.FIND_FREE_PLACE;

public class FestSpecificationPlaceFindAllFree implements FestSpecification {
    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_FREE_PLACE);
    }
}
