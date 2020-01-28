package by.beerfest.specification.impl;

import by.beerfest.constant.Query;
import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FestSpecificationParticipantFindByConfirmedIsFalse extends FestSpecification {
    public FestSpecificationParticipantFindByConfirmedIsFalse() {

    }

    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        return connection.prepareStatement(Query.FIND_UNCONFIRMED_PARTICIPANT);
    }
}
