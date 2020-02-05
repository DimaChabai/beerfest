package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.constant.PageParameter;
import by.beerfest.constant.PagePath;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.Place;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.ParticipantServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.beerfest.constant.PageParameter.*;

public class ToParticipantCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        ParticipantServiceImpl service = new ParticipantServiceImpl();
        List<Place> resultList = null;
        List<String> beers = null;
        try {
            beers = service.getBeers();
            content.setRequestAttribute(BEERTYPE, beers);
            resultList = service.getPlaces();
            content.setRequestAttribute(PLACES, resultList);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE,"page.message.places_load_error_message");
        }

        return PagePath.JSP_PARTICIPANT_JSP;
    }
}
