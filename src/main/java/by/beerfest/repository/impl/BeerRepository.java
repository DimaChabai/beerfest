package by.beerfest.repository.impl;

import by.beerfest.repository.Repository;
import by.beerfest.repository.RepositoryException;
import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.beerfest.constant.ColumnName.COL_BEERTYPE;

/**
 * Realization of {@code Repository} interface.
 * It is singleton.
 * Used to access the table 'beer'.
 */
public class BeerRepository extends Repository {

    private static BeerRepository instance = new BeerRepository();

    private BeerRepository() {
    }

    public static BeerRepository getInstance() {
        return instance;
    }

    /**
     * Executes a query passed in a {@code FestSpecification} object
     *
     * @param specification object that contain {@code Statement} fo query
     * @return Beer types {@code List}.
     * @throws RepositoryException if a database access error occurs;
     */
    public List<String> query(FestSpecification specification) throws RepositoryException {
        List<String> beers = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = specification.specified(connection);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                beers.add(resultSet.getString(COL_BEERTYPE));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return beers;
    }
}
