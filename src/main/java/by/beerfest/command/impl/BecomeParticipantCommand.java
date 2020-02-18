package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.controller.SessionRequestContent;
import by.beerfest.entity.UserRole;
import by.beerfest.service.ParticipantService;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.ParticipantServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.command.PageMessage.*;
import static by.beerfest.command.PageParameter.*;
import static by.beerfest.command.PagePath.JSP_MAIN_JSP;

/**
 * Realization of {@code Command} interface.
 * Has {@code Logger} object for logging error.
 * Uses @{code ParticipantServiceImpl} to create Participant
 */
public class BecomeParticipantCommand implements Command {

    private static Logger logger = LogManager.getLogger();
    private ParticipantService service = new ParticipantServiceImpl();

    /**
     * Gets user parameters from session and request to pass to the {@code ParticipantServiceImpl}
     *
     * @param content object that contain request, response and session information.
     * @return forward page
     */
    @Override
    public String execute(SessionRequestContent content) {
        boolean result = false;
        try {
            String companyName = content.getRequestParameter(NAME)[0];
            String placeName = content.getRequestParameter(PLACE)[0];
            Long id = (Long) content.getSessionAttribute(ID);
            String beerType = content.getRequestParameter(BEERTYPE)[0];
            result = service.addParticipant(companyName, placeName, id, beerType);
            content.setRequestAttribute(MESSAGE, PARTICIPANT_MESSAGE);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE, PARTICIPANT_ERROR_MESSAGE);
        }
        if (result) {
            content.setSessionAttribute(ROLE_NAME, UserRole.PARTICIPANT);
        } else {
            content.setRequestAttribute(ERROR_MESSAGE, INVALID_PARTICIPANT_DATA);
        }
        return JSP_MAIN_JSP;
    }
}
