package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.content.SessionRequestContent;
import by.chabai.service.TicketService;

public class TicketCommand implements Command {

    @Override
    public String execute(SessionRequestContent content) {
        TicketService service = new TicketService();
        return service.addGuest(content);
        //return JSP_MAIN_JSP; @TODO Нормально ли, что сервис возвращает страницу
    }
}
