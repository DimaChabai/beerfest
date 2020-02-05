package by.beerfest.repository.impl;

import by.beerfest.repository.Repository;
import by.beerfest.repository.RepositoryException;
import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.beerfest.constant.ColumnName.COL_BEERTYPE;

public class BeerRepository extends Repository {

    private static BeerRepository instance = new BeerRepository();

    public static BeerRepository getInstance() {
        return instance;
    }

    private BeerRepository() {
    }

    public List<String> query(FestSpecification specification) throws RepositoryException {
        ResultSet resultSet;
        List<String> beers = new ArrayList<>();;
        try(Connection connection = connectionPool.getConnection()) {
            resultSet = specification.specified(connection).executeQuery();
            while(resultSet.next()){
                beers.add(resultSet.getString(COL_BEERTYPE));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return beers;
    }
}
