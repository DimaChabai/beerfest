package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.controller.SessionRequestContent;
import by.beerfest.service.ServiceException;
import by.beerfest.service.UserService;
import by.beerfest.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.command.PageParameter.EMAIL;

public class PasswordRecoveryCommand implements Command {
    public static final String MESSAGE_RECEIVE_PASSWORD = "message.receive_password";
    private static final UserService userService = new UserServiceImpl();
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        String email = content.getRequestParameter(EMAIL)[0];
        try {
            userService.sendMessageOnEmail(email, MESSAGE_RECEIVE_PASSWORD);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return "/index.jsp";
    }


}
