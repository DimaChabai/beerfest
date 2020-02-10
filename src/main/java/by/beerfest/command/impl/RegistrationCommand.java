package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.UserServiceImpl;
import by.beerfest.servlet.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageMessage.*;
import static by.beerfest.constant.PageParameter.*;
import static by.beerfest.constant.PagePath.JSP_REGISTRATION_JSP;

/**
 * Realization of {@code Command} interface.
 * Has {@code Logger} object for logging error.
 * Registers {@code User}
 * using {@code RegistrationServiceImpl}.
 */
public class RegistrationCommand implements Command {

    private static Logger logger = LogManager.getLogger();
    private UserServiceImpl service = new UserServiceImpl();

    /**
     * Realization of {@code Command} interface.
     * Gets user parameters from request to pass to the {@code RegistrationServiceImpl}
     *
     * @param content object that contain request, response and session information.
     * @return forward page
     */
    @Override
    public String execute(SessionRequestContent content) {
        boolean result = false;
        boolean isCatch = false;
        try {
            String email = content.getRequestParameter(EMAIL)[0];
            String phoneNumber = content.getRequestParameter(PHONE_NUMBER)[0];
            String password = content.getRequestParameter(PASSWORD)[0];
            result = service.createAndAddUser(email, phoneNumber, password);
        } catch (ServiceException e) {
            logger.error(e);
            isCatch = true;
        }
        if (result) {
            content.setRequestAttribute(MESSAGE, REGISTRATION_SUCCESS);
        } else {
            String message = isCatch ? DATABASE_ERROR : INVALID_USER_DATA;
            content.setRequestAttribute(ERROR_MESSAGE, message);
        }
        return JSP_REGISTRATION_JSP;
    }
}
