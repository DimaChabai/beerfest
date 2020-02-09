package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.impl.Place;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.ParticipantServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.beerfest.constant.PageMessage.PLACES_LOAD_ERROR_MESSAGE;
import static by.beerfest.constant.PageParameter.*;
import static by.beerfest.constant.PagePath.JSP_BECOME_PARTICIPANT_JSP;

/**
 * Realization of {@code Command} interface.
 * Has {@code Logger} object for logging error.
 */
public class ToBecomeParticipantCommand implements Command {

    private Logger logger = LogManager.getLogger();
    private ParticipantServiceImpl service = new ParticipantServiceImpl();

    /**
     * Passes an array of beertypes and places to the request.
     *
     * @param content object that contain request, response and session information.
     * @return forward page
     */
    @Override
    public String execute(SessionRequestContent content) {
        try {
            List<String> beers = service.getBeers();
            content.setRequestAttribute(BEERTYPE, beers);
            List<Place> resultList = service.getPlaces();
            content.setRequestAttribute(PLACES, resultList);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE, PLACES_LOAD_ERROR_MESSAGE);
        }

        return JSP_BECOME_PARTICIPANT_JSP;
    }
}