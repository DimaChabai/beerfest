package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.entity.Place;
import by.chabai.pool.ConnectionPool;
import by.chabai.repository.PlaceRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationPlaceFindAllFree;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;

import static by.chabai.constant.PageParameter.PLACES;
import static by.chabai.constant.PagePath.JSP_PARTICIPANT_JSP;

public class ToParticipantCommand implements Command {

    private static PlaceRepository repository = PlaceRepository.getInstance();
    private static ConnectionPool connectionPool = ConnectionPool.INSTANCE;

    @Override
    public String execute(HttpServletRequest request) {
        Connection connection = connectionPool.getConnection();
        FestSpecification specification = new FestSpecificationPlaceFindAllFree(connection);
        List<Place> resultList = repository.query(specification);
        connectionPool.releaseConnection(connection);
        request.setAttribute(PLACES,resultList);
        return JSP_PARTICIPANT_JSP;
    }
}
