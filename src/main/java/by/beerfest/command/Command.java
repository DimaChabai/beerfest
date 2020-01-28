package by.beerfest.command;

import by.beerfest.content.SessionRequestContent;

public interface Command {
    String execute(SessionRequestContent content);
}
