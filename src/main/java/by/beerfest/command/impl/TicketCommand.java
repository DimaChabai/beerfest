package by.beerfest.command.impl;

import by.beerfest.constant.PageParameter;
import by.beerfest.command.Command;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.UserRole;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.TicketServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageParameter.ID;
import static by.beerfest.constant.PagePath.JSP_MAIN_JSP;
import static by.beerfest.constant.PagePath.JSP_TICKET_JSP;

public class TicketCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        TicketServiceImpl service = new TicketServiceImpl();
        boolean result = false;
        try {
            String defaultTicketString = content.getRequestParameter(PageParameter.DEFAULT_TICKET_NUMBER);
            String mediumTicketString = content.getRequestParameter(PageParameter.MEDIUM_TICKET_NUMBER);
            String largeTicketString = content.getRequestParameter(PageParameter.LARGE_TICKET_NUMBER);
            Long id = (Long) content.getSessionAttribute(ID);
            result = service.addGuest(defaultTicketString, mediumTicketString, largeTicketString, id);//@TODO сервис возвращает страницу
        } catch (ServiceException e) {
            logger.error(e);//@TODO Тут ошибка отправляется юзеру не в кетче
        }
        if (result) {
            content.setSessionAttribute(PageParameter.ROLE_NAME, UserRole.GUEST);
            return JSP_MAIN_JSP;
        } else {
            content.setRequestAttribute(PageParameter.ERROR_MESSAGE, "page.message.no_ticket_selected");
            return JSP_TICKET_JSP;
        }
    }
}
