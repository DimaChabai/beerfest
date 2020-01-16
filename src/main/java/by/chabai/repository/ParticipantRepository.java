package by.chabai.repository;

import by.chabai.entity.Participant;
import by.chabai.entity.Place;
import by.chabai.entity.PlaceType;
import by.chabai.entity.UserRole;
import by.chabai.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.chabai.constant.Query.*;

public class ParticipantRepository extends Repository {


    private static ParticipantRepository instance = new ParticipantRepository();

    public static ParticipantRepository getInstance() {
        return instance;
    }

    private ParticipantRepository() {
    }

    public void add(Participant participant) {
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(PARTICIPANT_INSERT);
            //@TODO правильно ли вынес запрос
            statement.setLong(1, participant.getId());
            statement.setString(2, participant.getName());
            statement.setLong(3, participant.getPlace().getId_place());
            statement.setBoolean(4, participant.isConfirmed());
            statement.executeUpdate();
            statement.close();
            statement = conn.prepareStatement(USER_TO_PARTICIPANT_UPDATE);
            statement.setLong(1, participant.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(conn);
        }
    }

    public void update(Participant participant){
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(PARTICIPANT_UPDATE);
            //@TODO Как вынести
            statement.setString(1,participant.getName());
            statement.setLong(2,participant.getPlace().getId_place());
            statement.setBoolean(3,participant.isConfirmed());
            statement.setLong(4,participant.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(conn);
        }
    }

    public void delete(Participant participant) {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_PARTICIPANT_BY_ID);
            statement.setLong(1, participant.getId());
            statement.executeUpdate();
            statement = connection.prepareStatement(PARTICIPANT_TO_USER_UPDATE);
            statement.setLong(1, participant.getId());
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }


    public List<Participant> query(FestSpecification specification) {
        List<Participant> resultList = new ArrayList<>();
        try {
            Participant participant;
            Place place;
            ResultSet resultSet = specification.specified().executeQuery();
            while (resultSet.next()) {
                participant = new Participant();
                participant.setId(resultSet.getLong(COL_ID_USER));
                participant.setName(resultSet.getString(COL_NAME));
                participant.setConfirmed(resultSet.getBoolean(COL_CONFIRMED));
                participant.setPassword(resultSet.getString(COL_PASSWORD));
                participant.setPhoneNumber(resultSet.getString(COL_PHONE_NUMBER));
                participant.setAvatar(resultSet.getString(COL_AVATAR));
                participant.setEmail(resultSet.getString(COL_EMAIL));
                participant.setRole(UserRole.valueOf(resultSet.getString(COL_ROLE_NAME)));
                place = new Place();
                place.setId_place(resultSet.getLong(COL_ID_PLACE));
                place.setType(PlaceType.valueOf(resultSet.getString(COL_TYPE)));
                place.setSeats(resultSet.getInt(COL_SEATS));
                participant.setPlace(place);
                resultList.add(participant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

}
