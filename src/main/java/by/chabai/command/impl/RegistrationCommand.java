package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.content.SessionRequestContent;
import by.chabai.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;

import static by.chabai.constant.PagePath.JSP_REGISTRATION_JSP;

public class RegistrationCommand implements Command {


    @Override
    public String execute(SessionRequestContent content) {
        RegistrationService service = new RegistrationService();
        service.addUser(content);
        return JSP_REGISTRATION_JSP;
    }
}
