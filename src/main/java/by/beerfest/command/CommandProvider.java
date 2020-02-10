package by.beerfest.command;

import by.beerfest.servlet.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.command.CommandType.MAIN;
import static by.beerfest.constant.PageParameter.COMMAND;

/**
 * Utility class for define command.
 * Has {@code Logger} object for logging error.
 */
public class CommandProvider {

    private CommandProvider() {
    }

    private static Logger logger = LogManager.getLogger();

    /**
     * Defines a command by its string representation that gets from request.
     * But there must be a corresponding element in enum {@code CommandType}
     *
     * @param content object that contain request, response and session information.
     * @return {@code Command} object
     */
    public static Command defineCommand(SessionRequestContent content) {
        Command current;
        String action = (String) content.getRequestAttribute(COMMAND);
        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            current = MAIN.getCurrentCommand();
            logger.error("Command definition error ", e);
        }
        return current;
    }
}
