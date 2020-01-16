package by.chabai.specification.impl;

import by.chabai.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.chabai.constant.Query.FIND_RESERVED_PLACE;

public class FestSpecificationPlaceFindAllReserved extends FestSpecification {
    public FestSpecificationPlaceFindAllReserved(Connection connection) {
        super(connection);
    }

    @Override
    public PreparedStatement specified() throws SQLException {
        return connection.prepareStatement(FIND_RESERVED_PLACE);
    }
}
