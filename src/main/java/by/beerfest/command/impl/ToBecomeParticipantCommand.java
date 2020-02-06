package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.constant.PagePath;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.impl.Place;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.ParticipantServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.beerfest.constant.PageMessage.PLACES_LOAD_ERROR_MESSAGE;
import static by.beerfest.constant.PageParameter.*;

public class ToBecomeParticipantCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        ParticipantServiceImpl service = new ParticipantServiceImpl();
        try {
            List<String> beers = service.getBeers();
            content.setRequestAttribute(BEERTYPE, beers);
            List<Place> resultList = service.getPlaces();
            content.setRequestAttribute(PLACES, resultList);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE, PLACES_LOAD_ERROR_MESSAGE);
        }

        return PagePath.JSP_BECOME_PARTICIPANT_JSP;
    }
}
