package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.constant.PageParameter;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.RegistrationServiceImpl;
import by.beerfest.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageMessage.*;
import static by.beerfest.constant.PageParameter.*;
import static by.beerfest.constant.PagePath.JSP_REGISTRATION_JSP;

public class RegistrationCommand implements Command {

   private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        RegistrationServiceImpl service = new RegistrationServiceImpl();
        boolean result = false;
        boolean isCatch = false;
        try {
            String email = content.getRequestParameter(EMAIL)[0];
            String phoneNumber = content.getRequestParameter(PHONE_NUMBER)[0];
            String password = content.getRequestParameter(PASSWORD)[0];
            result = service.createAndAddUser(email, phoneNumber, password);
        } catch (ServiceException e) {
            logger.error(e);
            isCatch=true;
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
