package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.content.SessionRequestContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.chabai.constant.PageParameter.LANGUAGE;
import static by.chabai.constant.PagePath.JSP_MAIN_JSP;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(SessionRequestContent content) {
        String language = content.getRequestParameter(LANGUAGE);
        content.setSessionAttribute(LANGUAGE,language);
        return JSP_MAIN_JSP;
    }
}
