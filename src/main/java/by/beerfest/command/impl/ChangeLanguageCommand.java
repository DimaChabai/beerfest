package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.constant.PageParameter;
import by.beerfest.content.SessionRequestContent;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(SessionRequestContent content) {
        String language = content.getRequestParameter(PageParameter.LANGUAGE);
        content.setSessionAttribute(PageParameter.LANGUAGE,language);
        return content.getRequestParameter("page");
    }
}
