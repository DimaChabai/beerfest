package by.beerfest.repository;

import by.beerfest.entity.TicketType;
import by.beerfest.pool.ConnectionPool;
import by.beerfest.specification.FestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static by.beerfest.constant.ColumnName.COL_TICKET_TYPE;
import static by.beerfest.constant.ColumnName.TICKET_SUM;
import static by.beerfest.constant.PageParameter.*;

public class TicketRepository extends Repository {

    public static ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static Logger logger = LogManager.getLogger();
    private static TicketRepository repository = new TicketRepository();

    public static TicketRepository getInstance() {
        return repository;
    }

    private TicketRepository() {
    }

    public Map<String, Integer> query(FestSpecification specification) throws RepositoryException {
        Map<String, Integer> resultMap = new HashMap<>();
        try (Connection connection = connectionPool.getConnection()) {
            ResultSet resultSet = specification.specified(connection).executeQuery();
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
            logger.error(e);
            throw new RepositoryException(e);
        }
        return resultMap;
    }

}
