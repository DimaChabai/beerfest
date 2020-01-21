package by.chabai.repository;

import by.chabai.entity.Participant;
import by.chabai.entity.Place;
import by.chabai.entity.PlaceType;
import by.chabai.entity.UserRole;
import by.chabai.pool.ProxyConnection;
import by.chabai.service.StatementService;
import by.chabai.specification.FestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.chabai.constant.ColumnName.*;
import static by.chabai.constant.Query.*;

public class ParticipantRepository extends Repository {

    private static Logger logger = LogManager.getLogger();

    private static ParticipantRepository instance = new ParticipantRepository();

    public static ParticipantRepository getInstance() {
        return instance;
    }

    private ParticipantRepository() {
    }

    public void add(Participant participant) {

        PreparedStatement statement = null;
        Connection conn = connectionPool.getConnection();
        try {
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(PARTICIPANT_INSERT);
            //@TODO правильно ли вынес запрос
            statement.setLong(1, participant.getId());
            statement.setString(2, participant.getName());
            statement.setLong(3, participant.getPlace().getIdPlace());
            statement.setBoolean(4, participant.isConfirmed());
            statement.executeUpdate();
            statement.close();
            statement = conn.prepareStatement(USER_TO_PARTICIPANT_UPDATE);
            statement.setLong(1, participant.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            ((ProxyConnection) conn).rollback();
        } finally {
            StatementService.commit(conn);
            ((ProxyConnection) conn).setAutoCommit(true);
            StatementService.closeStatement(statement);
        }
    }

    public void update(Participant participant) {

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(PARTICIPANT_UPDATE);) {
            //@TODO Как вынести
            statement.setString(1, participant.getName());
            statement.setLong(2, participant.getPlace().getIdPlace());
            statement.setBoolean(3, participant.isConfirmed());
            statement.setLong(4, participant.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }

    public void delete(Participant participant) {
        PreparedStatement statement = null;
        Connection connection = connectionPool.getConnection();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(DELETE_PARTICIPANT_BY_ID);
            statement.setLong(1, participant.getId());
            statement.executeUpdate();
            statement.close();
            statement = connection.prepareStatement(PARTICIPANT_TO_USER_UPDATE);
            statement.setLong(1, participant.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            logger.warn(e.getMessage());
            ((ProxyConnection)connection).rollback();
        } finally {
            StatementService.commit(connection);
            ((ProxyConnection)connection).setAutoCommit(true);
            StatementService.closeStatement(statement);
        }
    }


    public List<Participant> query(FestSpecification specification) {
        List<Participant> resultList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            Participant participant;
            Place place;
            ResultSet resultSet = specification.specified(connection).executeQuery();
            while (resultSet.next()) {
                participant = new Participant();//@TODO Вынести ли заполнение
                participant.setId(resultSet.getLong(COL_ID_USER));
                participant.setName(resultSet.getString(COL_NAME));
                participant.setConfirmed(resultSet.getBoolean(COL_CONFIRMED));
                participant.setPassword(resultSet.getString(COL_PASSWORD));
                participant.setPhoneNumber(resultSet.getString(COL_PHONE_NUMBER));
                participant.setAvatar(resultSet.getString(COL_AVATAR));
                participant.setEmail(resultSet.getString(COL_EMAIL));
                participant.setRole(UserRole.valueOf(resultSet.getString(COL_ROLE_NAME)));
                place = new Place();
                place.setIdPlace(resultSet.getLong(COL_ID_PLACE));
                place.setType(PlaceType.valueOf(resultSet.getString(COL_TYPE)));
                place.setSeats(resultSet.getInt(COL_SEATS));
                participant.setPlace(place);
                resultList.add(participant);
            }

        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return resultList;
    }

}
