package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.controller.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.command.PageParameter.ID;
import static by.beerfest.command.PagePath.ROOT_PAGE;

/**
 * Realization of {@code Command} interface.
 * Has {@code Logger} object for logging error.
 */
public class ExitCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    /**
     * Disable session.
     *
     * @param content object that contain request, response and session information.
     * @return forward page
     */
    @Override
    public String execute(SessionRequestContent content) {
        logger.info(String.format("User with id(%s) logged out", content.getSessionAttribute(ID)));
        content.invalidateSession();
        return ROOT_PAGE;
    }
}
