package by.beerfest.specification.impl;

import by.beerfest.specification.FestSpecification;
import by.beerfest.specification.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FestSpecificationPlaceFindAllReserved implements FestSpecification {
    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        return connection.prepareStatement(Query.FIND_RESERVED_PLACE);
    }
}
