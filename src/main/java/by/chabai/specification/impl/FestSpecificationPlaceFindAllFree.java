package by.chabai.specification.impl;

import by.chabai.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.chabai.constant.Query.FIND_FREE_PLACE;

public class FestSpecificationPlaceFindAllFree extends FestSpecification {

    public FestSpecificationPlaceFindAllFree(Connection connection) {
        super(connection);
    }

    @Override
    public PreparedStatement specified() throws SQLException {
        return connection.prepareStatement(FIND_FREE_PLACE);
    }
}
