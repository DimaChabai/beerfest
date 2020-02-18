package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.controller.SessionRequestContent;
import by.beerfest.entity.impl.User;
import by.beerfest.service.ServiceException;
import by.beerfest.service.UserService;
import by.beerfest.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Base64;

import static by.beerfest.command.PageMessage.INCORRECT_LOGIN_OR_PASSWORD;
import static by.beerfest.command.PageMessage.MESSAGE_DATABASE_ERROR;
import static by.beerfest.command.PageParameter.*;
import static by.beerfest.command.PagePath.JSP_LOGIN_JSP;
import static by.beerfest.command.PagePath.JSP_MAIN_JSP;

/**
 * Realization of {@code Command} interface.
 * Has {@code Logger} object for logging error.
 * Login {@code User}
 * using {@code LoginServiceImpl}.
 */
public class LoginCommand implements Command {

    private static Logger logger = LogManager.getLogger();
    private UserService service = new UserServiceImpl();
    private Base64.Encoder encoder = Base64.getEncoder();
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
            String hash = encoder.encodeToString(password.getBytes());
            user = service.authenticate(email, hash);
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
