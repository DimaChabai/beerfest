package by.beerfest.command;

import by.beerfest.content.SessionRequestContent;

/**
 * Realisation of Command pattern.
 * Every command must has a corresponding element in enum {@code CommandType}
 */
public interface Command {
    /**
     * Sets a common interface for specific command classes.
     *
     * @param content object that contain request, response and session information.
     * @return forward page
     */
    String execute(SessionRequestContent content);
}
