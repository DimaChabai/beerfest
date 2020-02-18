package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.controller.SessionRequestContent;
import by.beerfest.entity.impl.Participant;
import by.beerfest.service.ParticipantService;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.ParticipantServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.beerfest.command.PageMessage.PARTICIPANT_LOAD_ERROR_MESSAGE;
import static by.beerfest.command.PageParameter.ERROR_MESSAGE;
import static by.beerfest.command.PageParameter.PARTICIPANTS;
import static by.beerfest.command.PagePath.JSP_VERIFICATION_JSP;

/**
 * Realization of {@code Command} interface.
 * Has {@code Logger} object for logging error.
 */
public class ToVerificationCommand implements Command {

    private static Logger logger = LogManager.getLogger();
    private ParticipantService service = new ParticipantServiceImpl();

    /**
     * Passes an array of unconfirmed participants to the request.
     *
     * @param content object that contain request, response and session information.
     * @return forward page
     */
    @Override
    public String execute(SessionRequestContent content) {
        try {
            List<Participant> result = service.getVerificationPageContent();
            content.setRequestAttribute(PARTICIPANTS, result);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE, PARTICIPANT_LOAD_ERROR_MESSAGE);
        }
        return JSP_VERIFICATION_JSP;
    }
}
