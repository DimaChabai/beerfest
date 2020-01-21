package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.content.SessionRequestContent;
import by.chabai.service.ParticipantService;

import javax.servlet.http.HttpServletRequest;

import static by.chabai.constant.PagePath.JSP_MAIN_JSP;

public class ParticipantCommand implements Command {

    @Override
    public String execute(SessionRequestContent content) {
        ParticipantService service = new ParticipantService();
        service.addParticipant(content);
        return JSP_MAIN_JSP;
    }
}
