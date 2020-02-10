package by.beerfest.repository.impl;

import by.beerfest.entity.PlaceType;
import by.beerfest.entity.impl.Participant;
import by.beerfest.entity.impl.Place;
import by.beerfest.repository.Repository;
import by.beerfest.repository.RepositoryException;
import by.beerfest.service.UserService;
import by.beerfest.service.impl.UserServiceImpl;
import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.beerfest.constant.ColumnName.*;
import static by.beerfest.constant.Query.*;

/**
 * Realization of {@code Repository} interface.
 * It is singleton.
 * Used to access the table 'participant'.
 */
public class ParticipantRepository extends Repository {

    private static ParticipantRepository instance = new ParticipantRepository();
    private UserService userService = new UserServiceImpl();

    private ParticipantRepository() {
    }

    public static ParticipantRepository getInstance() {
        return instance;
    }

    /**
     * Adds a participant to the table 'participant'.
     *
     * @param participant object that contains information about participant
     * @throws SQLException        if a database access error occurs,
     *                             setAutoCommit(true) is called while participating in a distributed transaction,
     *                             or this method is called on a closed connection. Can be thrown from finally block.
     * @throws RepositoryException wraps SQLException
     */
    public void add(Participant participant) throws RepositoryException, SQLException {
        PreparedStatement statement = null;
        Connection conn = connectionPool.getConnection();
        try {
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(PARTICIPANT_INSERT);
            statement.setLong(1, participant.getId());
            statement.setString(2, participant.getName());
            statement.setLong(3, participant.getPlace().getIdPlace());
            statement.setBoolean(4, participant.isConfirmed());
            statement.executeUpdate();

            statement = conn.prepareStatement(INSERT_PARTICIPANT_BEER);
            statement.setLong(1, participant.getId());
            statement.setString(2, participant.getBeerType());
            statement.executeUpdate();

            statement = conn.prepareStatement(USER_TO_PARTICIPANT_UPDATE);
            statement.setLong(1, participant.getId());
            statement.executeUpdate();
            this.commit(conn);
        } catch (SQLException e) {
            conn.rollback();
            throw new RepositoryException(e);
        } finally {
            conn.setAutoCommit(true);
            this.closeStatement(statement);
        }
    }

    /**
     * Updates information about the participant in the "participant" table.
     *
     * @param participant object that contains information about participant
     * @throws SQLException        if a database access error occurs,
     *                             setAutoCommit(true) is called while participating in a distributed transaction,
     *                             or this method is called on a closed connection. Can be thrown from finally block.
     * @throws RepositoryException wraps SQLException
     */
    public void update(Participant participant) throws RepositoryException, SQLException {
        Connection conn = connectionPool.getConnection();
        PreparedStatement statement = null;
        try {
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(PARTICIPANT_UPDATE);
            statement.setString(1, participant.getName());
            statement.setLong(2, participant.getPlace().getIdPlace());
            statement.setBoolean(3, participant.isConfirmed());
            statement.setLong(4, participant.getId());
            statement.executeUpdate();
            statement.close();

            statement = conn.prepareStatement(PARTICIPANT_BEER_UPDATE);
            statement.setString(1, participant.getBeerType());
            statement.setLong(2, participant.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            conn.rollback();
            throw new RepositoryException(e);
        } finally {
            conn.setAutoCommit(true);
            this.closeStatement(statement);
        }
    }

    /**
     * Deletes the participant from the "participant" table.
     *
     * @param participant object that contains information about participant
     * @throws SQLException        if a database access error occurs,
     *                             setAutoCommit(true) is called while participating in a distributed transaction,
     *                             or this method is called on a closed connection. Can be thrown from finally block.
     * @throws RepositoryException wraps SQLException
     */
    public void delete(Participant participant) throws SQLException, RepositoryException {
        PreparedStatement statement = null;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(DELETE_PARTICIPANT_BY_ID);
            statement.setLong(1, participant.getId());
            statement.executeUpdate();
            statement.close();
            statement = connection.prepareStatement(DELETE_PARTICIPANT_BEER_BY_ID);
            statement.setLong(1, participant.getId());
            statement.executeUpdate();
            statement.close();
            statement = connection.prepareStatement(PARTICIPANT_TO_USER_UPDATE);
            statement.setLong(1, participant.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RepositoryException(e);
        } finally {
            connection.setAutoCommit(true);
            this.closeStatement(statement);
        }
    }

    /**
     * Executes a query passed in a {@code FestSpecification} object
     *
     * @param specification object that contain {@code Statement} for query
     * @return Participant {@code List}.
     * @throws RepositoryException if a database access error occurs;
     */
    public List<Participant> query(FestSpecification specification) throws RepositoryException {
        List<Participant> resultList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = specification.specified(connection);
             ResultSet resultSet = statement.executeQuery()) {
            Place place;
            while (resultSet.next()) {
                Participant participant = new Participant();
                userService.buildUser(resultSet, participant);
                participant.setName(resultSet.getString(COL_NAME));
                participant.setConfirmed(resultSet.getBoolean(COL_CONFIRMED));
                place = new Place();
                place.setIdPlace(resultSet.getLong(COL_ID_PLACE));
                place.setType(PlaceType.valueOf(resultSet.getString(COL_TYPE)));
                place.setSeats(resultSet.getInt(COL_SEATS));
                participant.setPlace(place);
                participant.setBeerType(resultSet.getString(COL_BEERTYPE));
                resultList.add(participant);
            }

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return resultList;
    }

}
