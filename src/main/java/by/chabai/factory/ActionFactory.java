package by.chabai.factory;

import by.chabai.command.Command;
import by.chabai.command.client.CommandEnum;
import by.chabai.command.impl.DefaultCommand;

import javax.servlet.http.HttpServletRequest;

import static by.chabai.constant.PageParameter.COMMAND;

public class ActionFactory {

    public Command defineCommand(HttpServletRequest request){
        Command current = new DefaultCommand();
// извлечение имени команды из запроса
        String action = request.getParameter(COMMAND);
        if (action == null || action.isEmpty()) {
// если команда не задана в текущем запросе
            return current;
        }
// получение объекта, соответствующего команде
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
            //@todo
        } catch (IllegalArgumentException e) {

        }
        return current;
    }
}
