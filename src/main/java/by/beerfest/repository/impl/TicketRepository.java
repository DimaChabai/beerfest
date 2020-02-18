package by.beerfest.repository.impl;

import by.beerfest.entity.TicketType;
import by.beerfest.repository.Repository;
import by.beerfest.repository.RepositoryException;
import by.beerfest.specification.FestSpecification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.Map;

import static by.beerfest.command.PageParameter.*;
import static by.beerfest.entity.TicketType.*;

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
    public Map<TicketType, Integer> query(FestSpecification specification) throws RepositoryException {
        Map<TicketType, Integer> resultMap = new EnumMap<>(TicketType.class);
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = specification.specified(connection);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            resultMap.put(DEFAULT, resultSet.getInt(BOOKED_DEFAULT_TICKET));
            resultMap.put(MEDIUM, resultSet.getInt(BOOKED_MEDIUM_TICKET));
            resultMap.put(LARGE, resultSet.getInt(BOOKED_LARGE_TICKET));
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return resultMap;
    }

}
