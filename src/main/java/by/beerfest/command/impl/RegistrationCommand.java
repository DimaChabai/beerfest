package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.constant.PageParameter;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.RegistrationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PagePath.JSP_REGISTRATION_JSP;

public class RegistrationCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        RegistrationServiceImpl service = new RegistrationServiceImpl();
        boolean result = false;
        try {
            result = service.addUser(content.getRequestParameter(PageParameter.EMAIL), content.getRequestParameter(PageParameter.PHONE_NUMBER), content.getRequestParameter(PageParameter.PASSWORD));
        } catch (ServiceException e) {
            logger.error(e);//@TODO Тут ошибка отправляется юзеру не в кетче
        }
        if (result) {
            content.setRequestAttribute(PageParameter.MESSAGE, "page.message.registration_success");
        } else {
            content.setRequestAttribute(PageParameter.ERROR_MESSAGE, "page.message.taken_email");
        }
        return JSP_REGISTRATION_JSP;
    }
}
