package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.service.ServiceException;
import by.beerfest.service.TicketService;
import by.beerfest.service.impl.TicketServiceImpl;
import by.beerfest.servlet.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.beerfest.constant.PageMessage.TICKET_LOAD_ERROR_MESSAGE;
import static by.beerfest.constant.PageParameter.ERROR_MESSAGE;
import static by.beerfest.constant.PagePath.JSP_TICKET_JSP;

/**
 * Realization of {@code Command} interface.
 * Has {@code Logger} object for logging error.
 */
public class ToTicketBookCommand implements Command {

    private static Logger logger = LogManager.getLogger();
    private TicketService service = new TicketServiceImpl();

    /**
     * Passes an array of tickets to the request.
     *
     * @param content object that contain request, response and session information.
     * @return forward page
     */
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
