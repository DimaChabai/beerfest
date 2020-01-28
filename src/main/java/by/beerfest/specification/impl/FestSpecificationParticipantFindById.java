package by.beerfest.specification.impl;

import by.beerfest.constant.Query;
import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FestSpecificationParticipantFindById extends FestSpecification {

    private long id;

    public FestSpecificationParticipantFindById( long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(Query.FIND_PARTICIPANT_BY_ID);
        statement.setLong(1, id);
        return statement;
    }
}
