package by.chabai.command;

import by.chabai.command.Command;
import by.chabai.command.CommandType;
import by.chabai.command.impl.DefaultCommand;
import by.chabai.content.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.chabai.constant.PageParameter.COMMAND;

public class ActionFactory {

    private static Logger logger = LogManager.getLogger();

    public static Command defineCommand(SessionRequestContent content){
        Command current = null;
        String action = (String) content.getRequestAttribute(COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            current = new DefaultCommand();
            logger.warn("Ошибка определения команды " + e.getMessage());
        }
        return current;
    }
}
