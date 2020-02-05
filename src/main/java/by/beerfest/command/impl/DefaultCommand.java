package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.service.ServiceException;

import static by.beerfest.constant.PagePath.ROOT_PAGE;

public class DefaultCommand implements Command {
    @Override
    public String execute(SessionRequestContent content) {
        return ROOT_PAGE;
    }
}
