package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.controller.SessionRequestContent;

import static by.beerfest.command.PageParameter.LOCALE;

/**
 * Realization of {@code Command} interface.
 * Change locale
 */
public class ChangeLocaleCommand implements Command {
    @Override
    public String execute(SessionRequestContent content) {
        String locale = content.getRequestParameter(LOCALE)[0];
        content.setSessionAttribute(LOCALE, locale);
        return content.getRequestParameter("page")[0];
    }
}
