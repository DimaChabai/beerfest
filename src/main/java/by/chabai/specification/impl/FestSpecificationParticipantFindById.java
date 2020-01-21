package by.chabai.specification.impl;

import by.chabai.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.chabai.constant.Query.FIND_PARTICIPANT_BY_ID;

public class FestSpecificationParticipantFindById extends FestSpecification {

    private long id;

    public FestSpecificationParticipantFindById( long id) {
        this.id = id;
    }

    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_PARTICIPANT_BY_ID);
        statement.setLong(1, id);
        return statement;
    }
}
