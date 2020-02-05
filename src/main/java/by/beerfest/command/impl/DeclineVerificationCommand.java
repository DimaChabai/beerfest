package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.constant.PageParameter;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.Participant;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.VerificationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.beerfest.constant.PageParameter.*;
import static by.beerfest.constant.PagePath.JSP_VERIFICATION_JSP;

public class DeclineVerificationCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        VerificationServiceImpl service = new VerificationServiceImpl();
        try {
            long id = Long.parseLong(content.getRequestParameter(ID)[0]);
            service.decline(id);
            fillPage(content);
            content.setRequestAttribute(MESSAGE, "page.message.decline_verification_message");
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE, "page.message.decline_verification_error_message");
        }
        return JSP_VERIFICATION_JSP;
    }

    private void fillPage(SessionRequestContent content) throws ServiceException {
        VerificationServiceImpl service = new VerificationServiceImpl();
        List<Participant> result = service.fillVerificationPage();
        content.setRequestAttribute(PARTICIPANTS, result);
    }
}
