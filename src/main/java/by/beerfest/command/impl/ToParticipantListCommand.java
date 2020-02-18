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
import static by.beerfest.command.PageParameter.*;
import static by.beerfest.command.PagePath.JSP_PARTICIPANT_LIST_JSP;

/**
 * Realization of {@code Command} interface.
 * Has {@code Logger} object for logging error.
 */
public class ToParticipantListCommand implements Command {

    private static final int cardPerPage = 6;
    private static Logger logger = LogManager.getLogger();
    private ParticipantService service = new ParticipantServiceImpl();

    /**
     * Passes an array of confirmed participants to the request.
     * Implements page pagination.
     *
     * @param content object that contain request, response and session information.
     * @return forward page
     */
    @Override
    public String execute(SessionRequestContent content) {
        try {
            String[] s = content.getRequestParameter(PAGE);
            int page = 1;
            if (s != null) {
                page = Integer.parseInt(s[0]);
                if (page < 1) {
                    page = 1;
                }
            }
            content.setRequestAttribute(PAGE, page);
            List<Participant> participants = service.getParticipantsFromTo((page - 1) * cardPerPage, page * cardPerPage);
            content.setRequestAttribute(PARTICIPANTS, participants);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE, PARTICIPANT_LOAD_ERROR_MESSAGE);
        }
        return JSP_PARTICIPANT_LIST_JSP;
    }
}
