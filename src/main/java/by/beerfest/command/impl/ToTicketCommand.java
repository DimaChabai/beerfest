package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.TicketServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.beerfest.constant.PageMessage.TICKET_LOAD_ERROR_MESSAGE;
import static by.beerfest.constant.PageParameter.ERROR_MESSAGE;
import static by.beerfest.constant.PagePath.JSP_TICKET_JSP;


public class ToTicketCommand implements Command {

    private Logger logger = LogManager.getLogger();
    private TicketServiceImpl service = new TicketServiceImpl();

    @Override
    public String execute(SessionRequestContent content) {
        try {
            Map<String, Integer> ticketNumber = service.calculateTicketNumber();
            ticketNumber.forEach(content::setRequestAttribute);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE, TICKET_LOAD_ERROR_MESSAGE);
        }
        return JSP_TICKET_JSP;
    }
}
