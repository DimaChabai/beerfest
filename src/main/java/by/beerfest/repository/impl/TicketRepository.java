package by.beerfest.repository.impl;

import by.beerfest.entity.TicketType;
import by.beerfest.repository.Repository;
import by.beerfest.repository.RepositoryException;
import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static by.beerfest.constant.ColumnName.COL_TICKET_TYPE;
import static by.beerfest.constant.ColumnName.TICKET_SUM;
import static by.beerfest.constant.PageParameter.*;
/**
 * Realization of {@code Repository} interface.
 * It is singleton.
 * Used to access the table 'guest_ticket'.
 */
public class TicketRepository extends Repository {

    private static TicketRepository repository = new TicketRepository();

    private TicketRepository() {
    }

    public static TicketRepository getInstance() {
        return repository;
    }

    /**
     * Executes a query passed in a {@code FestSpecification} object
     *
     * @param specification object that contain {@code Statement} fo query
     * @return {@code Map} with the type of the ticket as the key and the number of tickets as the value.
     * @throws RepositoryException if a database access error occurs;
     */
    public Map<String, Integer> query(FestSpecification specification) throws RepositoryException {
        Map<String, Integer> resultMap = new HashMap<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = specification.specified(connection);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                switch (TicketType.valueOf(resultSet.getString(COL_TICKET_TYPE))) {
                    case DEFAULT:
                        resultMap.put(BOOKED_DEFAULT_TICKET, resultSet.getInt(TICKET_SUM));
                        break;
                    case MEDIUM:
                        resultMap.put(BOOKED_MEDIUM_TICKET, resultSet.getInt(TICKET_SUM));
                        break;
                    case LARGE:
                        resultMap.put(BOOKED_LARGE_TICKET, resultSet.getInt(TICKET_SUM));
                        break;
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return resultMap;
    }

}
