package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.content.SessionRequestContent;

import javax.servlet.http.HttpServletRequest;

import static by.chabai.constant.PagePath.ROOT_PAGE;

public class DefaultCommand implements Command {
    @Override
    public String execute(SessionRequestContent content) {
        return ROOT_PAGE;
    }
}
