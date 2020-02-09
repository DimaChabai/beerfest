package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.impl.User;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.LoginServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageMessage.INCORRECT_LOGIN_OR_PASSWORD;
import static by.beerfest.constant.PageMessage.MESSAGE_DATABASE_ERROR;
import static by.beerfest.constant.PageParameter.*;
import static by.beerfest.constant.PagePath.JSP_LOGIN_JSP;
import static by.beerfest.constant.PagePath.JSP_MAIN_JSP;

/**
 * Realization of {@code Command} interface.
 * Has {@code Logger} object for logging error.
 * Login {@code User}
 * using {@code LoginServiceImpl}.
 */
public class LoginCommand implements Command {

    private Logger logger = LogManager.getLogger();
    private LoginServiceImpl service = new LoginServiceImpl();

    /**
     * Gets user parameters from request to pass to the {@code LoginServiceImpl}
     *
     * @param content object that contain request, response and session information.
     * @return forward page
     */
    @Override
    public String execute(SessionRequestContent content) {
        User user = null;
        boolean isCatch = false;
        try {
            String email = content.getRequestParameter(EMAIL)[0];
            String password = content.getRequestParameter(PASSWORD)[0];
            user = service.login(email, password);
        } catch (ServiceException e) {
            logger.error(e);
            isCatch = true;
        }
        if (user != null) {
            content.setSessionAttribute(ID, user.getId());
            content.setSessionAttribute(EMAIL, user.getEmail());
            content.setSessionAttribute(ROLE_NAME, user.getRole());
            content.setSessionAttribute(PHONE_NUMBER, user.getPhoneNumber());
            content.setSessionAttribute(AVATAR, user.getAvatar());
            logger.info(user + " logged in");
            return JSP_MAIN_JSP;
        } else {
            String message = isCatch ? MESSAGE_DATABASE_ERROR : INCORRECT_LOGIN_OR_PASSWORD;
            content.setRequestAttribute(ERROR_MESSAGE, message);
            return JSP_LOGIN_JSP;
        }
    }
}
