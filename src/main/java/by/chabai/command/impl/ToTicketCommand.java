package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.entity.Place;
import by.chabai.entity.PlaceType;
import by.chabai.entity.TicketType;
import by.chabai.pool.ConnectionPool;
import by.chabai.repository.GuestRepository;
import by.chabai.repository.PlaceRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationPlaceFindAllReserved;
import by.chabai.specification.impl.FestSpecificationTicketFindAllGroupByType;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static by.chabai.constant.PageParameter.*;
import static by.chabai.constant.PagePath.JSP_TICKET_JSP;
import static by.chabai.constant.Query.COL_SUM;
import static by.chabai.constant.Query.COL_TICKET_TYPE;

public class ToTicketCommand implements Command {


    private static PlaceRepository placeRepository = PlaceRepository.getInstance();
    private static ConnectionPool connectionPool = ConnectionPool.INSTANCE;

    @Override
    public String execute(HttpServletRequest request) {
        Connection connection = connectionPool.getConnection();

        FestSpecification specification = new FestSpecificationPlaceFindAllReserved(connection);
        List<Place> resultList = placeRepository.query(specification);

        specification = new FestSpecificationTicketFindAllGroupByType(connection);//@TODO надо ли выносить в отдельную спецификацию или вернуть всё обратно?
        try {
            ResultSet resultSet = specification.specified().executeQuery();
            int bookedDefaultTicket = 0;
            int bookedMediumTicket = 0;
            int bookedLargeTicket = 0;
            while (resultSet.next()){
                switch (TicketType.valueOf(resultSet.getString(COL_TICKET_TYPE))){
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
            int defaultTicketCount = resultList.stream().filter((v)->v.getType().equals(PlaceType.SMALL)).reduce(0,
                    (v,p)->v+p.getSeats(),
                    Integer::sum) - bookedDefaultTicket;
            int mediumTicketCount = resultList.stream().filter((v)->v.getType().equals(PlaceType.MEDIUM)).reduce(0,
                    (v,p)->v+p.getSeats(),
                    Integer::sum) - bookedMediumTicket;
            int largeTicketCount = resultList.stream().filter((v)->v.getType().equals(PlaceType.LARGE)).reduce(0,
                    (v,p)->v+p.getSeats(),
                    Integer::sum) - bookedLargeTicket;
            request.setAttribute(DEFAULT_TICKET_NUMBER,defaultTicketCount);
            request.setAttribute(MEDIUM_TICKET_NUMBER,mediumTicketCount);
            request.setAttribute(LARGE_TICKET_NUMBER,largeTicketCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionPool.releaseConnection(connection);

        return JSP_TICKET_JSP;
    }
}
