package by.beerfest.command;

import by.beerfest.content.SessionRequestContent;
import by.beerfest.service.ServiceException;

public interface Command {
     String execute(SessionRequestContent content);
}
