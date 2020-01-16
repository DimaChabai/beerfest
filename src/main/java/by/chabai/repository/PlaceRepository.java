package by.chabai.repository;

import by.chabai.entity.Place;
import by.chabai.entity.PlaceType;
import by.chabai.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.chabai.constant.Query.*;

public class PlaceRepository extends Repository {

    private static PlaceRepository instance = new PlaceRepository();

    public static PlaceRepository getInstance() {
        return instance;
    }

    private PlaceRepository() {
    }

    public void add(Place place) {
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(PLACE_INSERT);
            //@TODO правильно ли вынес запрос
            statement.setString(1, place.getType().toString());
            statement.setLong(2, place.getSeats());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(conn);
        }
    }

    public List<Place> query(FestSpecification specification) {
        ResultSet resultSet = null;
        List<Place> resultList = new ArrayList<>();
        try {
            resultSet = specification.specified().executeQuery();
            Place place;
            while (resultSet.next()) {
                place = new Place();
                place.setSeats(resultSet.getInt(COL_SEATS));
                place.setType(PlaceType.valueOf(resultSet.getString(COL_TYPE)));
                place.setId_place(resultSet.getLong(COL_ID_PLACE));
                resultList.add(place);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resultList.sort((v,p)-> (int) (v.getId_place()-p.getId_place()));
        return resultList;
    }
}
