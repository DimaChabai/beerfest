package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.entity.Participant;
import by.chabai.entity.Place;
import by.chabai.entity.UserRole;
import by.chabai.pool.ConnectionPool;
import by.chabai.repository.ParticipantRepository;
import by.chabai.repository.PlaceRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationPlaceFindById;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.chabai.constant.PageParameter.*;
import static by.chabai.constant.PagePath.JSP_MAIN_JSP;

public class ParticipantCommand implements Command {

    private static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static final ParticipantRepository participantRepository = ParticipantRepository.getInstance();
    private static final PlaceRepository placeRepository = PlaceRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = request.getParameter(NAME);
        String placeName = request.getParameter(PLACE);
        Participant participant = new Participant();

        Pattern pattern = Pattern.compile("№\\d");//@TODO
        Matcher matcher = pattern.matcher(placeName);
        matcher.find();
        String idPlace = matcher.group().substring(1);

        Connection connection = connectionPool.getConnection();
        FestSpecification specification = new FestSpecificationPlaceFindById(connection,Long.parseLong(idPlace));
        Place place = placeRepository.query(specification).get(0);//@TODO
        connectionPool.releaseConnection(connection);
        participant.setPlace(place);
        participant.setConfirmed(false);
        participant.setName(name);
        participant.setId((Long) session.getAttribute(ID));
        session.setAttribute(ROLE_NAME, UserRole.PARTICIPANT);//@TODO
        participantRepository.add(participant);
        return JSP_MAIN_JSP;
    }
}
