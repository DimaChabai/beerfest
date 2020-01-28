package by.beerfest.command.impl;

import by.beerfest.constant.PageParameter;
import by.beerfest.command.Command;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.CreateServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageParameter.ERROR_MESSAGE;
import static by.beerfest.constant.PagePath.JSP_CREATE_JSP;

public class CreateCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        CreateServiceImpl service = new CreateServiceImpl();
        try {
            service.createPlace(content.getRequestParameter(PageParameter.PLACE_TYPE), content.getRequestParameter(PageParameter.SEATS));
        } catch (ServiceException e) {
            logger.error(e);
            content.setSessionAttribute(ERROR_MESSAGE,"page.message.create_place_error_message");
        }
        return JSP_CREATE_JSP;
    }
}
