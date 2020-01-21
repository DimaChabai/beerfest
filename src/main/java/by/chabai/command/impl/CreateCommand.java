package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.content.SessionRequestContent;
import by.chabai.service.CreateService;

import javax.servlet.http.HttpServletRequest;

import static by.chabai.constant.PagePath.JSP_CREATE_JSP;

public class CreateCommand implements Command {

    @Override
    public String execute(SessionRequestContent content) {
        CreateService service = new CreateService();
        service.createPlace(content);
        return JSP_CREATE_JSP;
    }
}
