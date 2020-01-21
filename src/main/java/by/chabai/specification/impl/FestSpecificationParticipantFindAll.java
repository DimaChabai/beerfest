package by.chabai.specification.impl;

import by.chabai.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.chabai.constant.Query.FIND_ALL_PARTICIPANT;

public class FestSpecificationParticipantFindAll extends FestSpecification {

    @Override
    public PreparedStatement specified(Connection connection) throws SQLException {
        return connection.prepareStatement(FIND_ALL_PARTICIPANT);
    }
}
