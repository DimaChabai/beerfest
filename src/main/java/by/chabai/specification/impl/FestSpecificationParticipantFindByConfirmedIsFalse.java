package by.chabai.specification.impl;

import by.chabai.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.chabai.constant.Query.FIND_UNCONFIRMED_PARTICIPANT;

public class FestSpecificationParticipantFindByConfirmedIsFalse extends FestSpecification {
    public FestSpecificationParticipantFindByConfirmedIsFalse(Connection connection) {
        super(connection);
    }

    @Override
    public PreparedStatement specified() throws SQLException {
        return connection.prepareStatement(FIND_UNCONFIRMED_PARTICIPANT);
    }
}
