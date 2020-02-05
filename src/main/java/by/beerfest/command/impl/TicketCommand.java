package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.constant.PageParameter;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.UserRole;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.TicketServiceImpl;
import by.beerfest.validator.TicketValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageParameter.*;
import static by.beerfest.constant.PagePath.JSP_MAIN_JSP;
import static by.beerfest.constant.PagePath.JSP_TICKET_JSP;
import static by.beerfest.entity.UserRole.GUEST;

public class TicketCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        TicketServiceImpl service = new TicketServiceImpl();
        boolean result = false;
        boolean isCatch = false;
        try {
            String defaultTicketString = content.getRequestParameter(DEFAULT_TICKET_NUMBER)[0];
            String mediumTicketString = content.getRequestParameter(MEDIUM_TICKET_NUMBER)[0];
            String largeTicketString = content.getRequestParameter(LARGE_TICKET_NUMBER)[0];
            TicketValidator validator = new TicketValidator();
            if(validator.validate(defaultTicketString)
                    || validator.validate(mediumTicketString)
                    || validator.validate(largeTicketString)){
                Long id = (Long) content.getSessionAttribute(ID);
                result = service.addGuest(defaultTicketString, mediumTicketString, largeTicketString, id);
            }
        } catch (ServiceException e) {
            logger.error(e);
            isCatch = true;
        }
        if (result) {
            content.setSessionAttribute(ROLE_NAME, GUEST);
            content.setRequestAttribute(MESSAGE,"page.message.ticket_reservation_success");
            return JSP_MAIN_JSP;
        } else {
            String message = isCatch ? "page.message.database_error" : "page.message.no_ticket_selected";
            content.setRequestAttribute(ERROR_MESSAGE, message);
            return JSP_TICKET_JSP;
        }
    }
}
