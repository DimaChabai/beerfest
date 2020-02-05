package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.constant.PageParameter;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.User;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.LoginServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageParameter.*;
import static by.beerfest.constant.PagePath.JSP_LOGIN_JSP;
import static by.beerfest.constant.PagePath.JSP_MAIN_JSP;

public class LoginCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        LoginServiceImpl service = new LoginServiceImpl();
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
            String message = isCatch ? "page.message.database_error" : "page.message.incorrect_login_or_password";
            content.setRequestAttribute(ERROR_MESSAGE, message);
            return JSP_LOGIN_JSP;
        }
    }
}
