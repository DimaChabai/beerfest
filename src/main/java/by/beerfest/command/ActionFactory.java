package by.beerfest.command;

import by.beerfest.command.impl.DefaultCommand;
import by.beerfest.constant.PageParameter;
import by.beerfest.content.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageParameter.COMMAND;

public class ActionFactory {

    private static Logger logger = LogManager.getLogger();

    public static Command defineCommand(SessionRequestContent content) {
        Command current;
        String action = (String) content.getRequestAttribute(COMMAND);
        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            current = new DefaultCommand();
            logger.error("Command definition error ", e);
        }
        return current;
    }
}
