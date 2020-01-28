package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.constant.PageParameter;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.Participant;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.VerificationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static by.beerfest.constant.PageParameter.ERROR_MESSAGE;
import static by.beerfest.constant.PagePath.JSP_VERIFICATION_JSP;

public class AcceptVerificationCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {

        VerificationServiceImpl service = new VerificationServiceImpl();
        try {

            service.accept(Long.parseLong(content.getRequestParameter(PageParameter.ID)));
//@TODO повторение кода
            service = new VerificationServiceImpl();
            List<Participant> result = service.fillVerificationPage();
            content.setRequestAttribute(PageParameter.PARTICIPANTS, result);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE,"page.message.accept_verification_error_message");
        }


        return JSP_VERIFICATION_JSP;
    }
}
