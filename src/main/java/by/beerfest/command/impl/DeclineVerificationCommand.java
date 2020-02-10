package by.beerfest.command.impl;

import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.ParticipantServiceImpl;
import by.beerfest.servlet.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageMessage.DECLINE_VERIFICATION_ERROR_MESSAGE;
import static by.beerfest.constant.PageMessage.DECLINE_VERIFICATION_MESSAGE;
import static by.beerfest.constant.PageParameter.*;
import static by.beerfest.constant.PagePath.JSP_VERIFICATION_JSP;

/**
 * Realization of {@code Command} interface.
 * Has {@code Logger} object for logging error.
 * Declines verification {@code Participant}
 * using {@code VerificationServiceImpl}.
 */
public class DeclineVerificationCommand extends VerificationCommand {

    private static Logger logger = LogManager.getLogger();
    private ParticipantServiceImpl service = new ParticipantServiceImpl();

    /**
     * Call method decline of class {@code VerificationServiceImpl}
     * and send message according to result of declining.
     *
     * @param content object that contain request, response and session information.
     * @return forward page
     */
    @Override
    public String execute(SessionRequestContent content) {
        try {
            long id = Long.parseLong(content.getRequestParameter(ID)[0]);
            service.decline(id);
            fillPage(content);
            content.setRequestAttribute(MESSAGE, DECLINE_VERIFICATION_MESSAGE);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE, DECLINE_VERIFICATION_ERROR_MESSAGE);
        }
        return JSP_VERIFICATION_JSP;
    }
}
