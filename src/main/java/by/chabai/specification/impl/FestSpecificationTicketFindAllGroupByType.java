package by.chabai.specification.impl;

import by.chabai.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.chabai.constant.Query.FIND_TICKET_GROUP_BY_TYPE;

public class FestSpecificationTicketFindAllGroupByType extends FestSpecification {

    public FestSpecificationTicketFindAllGroupByType() {

    }

    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_TICKET_GROUP_BY_TYPE);
    }
}
