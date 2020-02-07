package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.content.SessionRequestContent;

import static by.beerfest.constant.PageParameter.LOCALE;

public class ChangeLocaleCommand implements Command {
    @Override
    public String execute(SessionRequestContent content) {
        String locale = content.getRequestParameter(LOCALE)[0];
        content.setSessionAttribute(LOCALE,locale);
        return content.getRequestParameter("page")[0];
    }
}
