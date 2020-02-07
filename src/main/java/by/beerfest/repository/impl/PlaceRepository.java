package by.beerfest.repository.impl;

import by.beerfest.constant.Query;
import by.beerfest.entity.PlaceType;
import by.beerfest.entity.impl.Place;
import by.beerfest.repository.Repository;
import by.beerfest.repository.RepositoryException;
import by.beerfest.specification.FestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.beerfest.constant.ColumnName.*;
import static by.beerfest.constant.Query.PLACE_INSERT;

public class PlaceRepository extends Repository {

    private static Logger logger = LogManager.getLogger();
    private static PlaceRepository instance = new PlaceRepository();

    public static PlaceRepository getInstance() {
        return instance;
    }

    private PlaceRepository() {
    }

    public void add(Place place) throws RepositoryException {
        try (Connection conn = connectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(PLACE_INSERT)) {
            statement.setString(1, place.getType().toString());
            statement.setLong(2, place.getSeats());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    public List<Place> query(FestSpecification specification) throws RepositoryException {

        List<Place> resultList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = specification.specified(connection);
             ResultSet resultSet = statement.executeQuery()) {
            Place place;
            while (resultSet.next()) {
                place = new Place();
                place.setSeats(resultSet.getInt(COL_SEATS));
                place.setType(PlaceType.valueOf(resultSet.getString(COL_TYPE)));
                place.setIdPlace(resultSet.getLong(COL_ID_PLACE));
                resultList.add(place);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        resultList.sort((v, p) -> (int) (v.getIdPlace() - p.getIdPlace()));
        return resultList;
    }
}
