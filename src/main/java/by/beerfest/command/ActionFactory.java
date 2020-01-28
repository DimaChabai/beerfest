package by.beerfest.command;

import by.beerfest.command.impl.DefaultCommand;
import by.beerfest.constant.PageParameter;
import by.beerfest.content.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionFactory {

    private static Logger logger = LogManager.getLogger();

    public static Command defineCommand(SessionRequestContent content){
        Command current;
        String action = (String) content.getRequestAttribute(PageParameter.COMMAND);
        if (action == null || action.isEmpty()) {
            return new DefaultCommand();
        }
        try {//@TODO бросать исключение дальше?
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            current = new DefaultCommand();//@TODO обнулять сессию?
            logger.error("Command definition error " + e);
        }
        return current;
    }
}
