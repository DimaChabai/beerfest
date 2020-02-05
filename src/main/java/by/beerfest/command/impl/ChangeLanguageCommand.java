package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.constant.PageParameter;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.service.ServiceException;

import static by.beerfest.constant.PageParameter.LANGUAGE;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(SessionRequestContent content) {
        String language = content.getRequestParameter(LANGUAGE)[0];
        content.setSessionAttribute(LANGUAGE,language);
        return content.getRequestParameter("page")[0];
    }
}
