package by.chabai.repository;

import by.chabai.dto.TicketCountDTO;
import by.chabai.entity.Place;
import by.chabai.entity.PlaceType;
import by.chabai.entity.TicketType;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationTicketFindAllGroupByType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static by.chabai.constant.ColumnName.COL_SUM;
import static by.chabai.constant.ColumnName.COL_TICKET_TYPE;

public class TicketRepository extends Repository {

    private static Logger logger = LogManager.getLogger();
    private static TicketRepository repository = new TicketRepository();

    public static TicketRepository getInstance() {
        return repository;
    }

    private TicketRepository() {
    }

    public TicketCountDTO calculateTicketCount(List<Place> resultList) {
        FestSpecification specification;
        specification = new FestSpecificationTicketFindAllGroupByType();//@TODO надо ли выносить в отдельную спецификацию или оставить? Ведь нет репозитория подходящего
        try (Connection connection = connectionPool.getConnection()) {
            ResultSet resultSet = specification.specified(connection).executeQuery();
            int bookedDefaultTicket = 0;
            int bookedMediumTicket = 0;
            int bookedLargeTicket = 0;
            while (resultSet.next()) {
                switch (TicketType.valueOf(resultSet.getString(COL_TICKET_TYPE))) {
                    case DEFAULT:
                        bookedDefaultTicket = resultSet.getInt(COL_SUM);
                        break;
                    case LARGE:
                        bookedLargeTicket = resultSet.getInt(COL_SUM);
                        break;
                    case MEDIUM:
                        bookedMediumTicket = resultSet.getInt(COL_SUM);
                        break;
                }
            }
            int defaultTicketCount = resultList.stream().filter((v) -> v.getType().equals(PlaceType.SMALL)).reduce(0,
                    (v, p) -> v + p.getSeats(),
                    Integer::sum) - bookedDefaultTicket;
            int mediumTicketCount = resultList.stream().filter((v) -> v.getType().equals(PlaceType.MEDIUM)).reduce(0,
                    (v, p) -> v + p.getSeats(),
                    Integer::sum) - bookedMediumTicket;
            int largeTicketCount = resultList.stream().filter((v) -> v.getType().equals(PlaceType.LARGE)).reduce(0,
                    (v, p) -> v + p.getSeats(),
                    Integer::sum) - bookedLargeTicket;
            return new TicketCountDTO(defaultTicketCount, mediumTicketCount, largeTicketCount);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return new TicketCountDTO();//@TODO мб лучше обойтись без dto и сразу в реквест вставлять данные
    }

}
