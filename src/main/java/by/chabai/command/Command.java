package by.chabai.command;

import by.chabai.content.SessionRequestContent;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(SessionRequestContent content);
}
