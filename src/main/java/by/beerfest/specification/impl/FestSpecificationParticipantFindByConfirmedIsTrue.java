package by.beerfest.specification.impl;

import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.beerfest.constant.Query.FIND_CONFIRMED_PARTICIPANT_FROM_TO;

public class FestSpecificationParticipantFindByConfirmedIsTrue extends FestSpecification {
    private int start;
    private int end;

    public FestSpecificationParticipantFindByConfirmedIsTrue(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(FIND_CONFIRMED_PARTICIPANT_FROM_TO);
        statement.setInt(1,start);
        statement.setInt(2,end);
        return statement;
    }
}
