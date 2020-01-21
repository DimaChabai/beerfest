package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.content.SessionRequestContent;
import by.chabai.repository.ParticipantRepository;
import by.chabai.service.VerificationService;

import javax.servlet.http.HttpServletRequest;

import static by.chabai.constant.PagePath.JSP_VERIFICATION_JSP;

public class AcceptVerificationCommand implements Command {

    @Override
    public String execute(SessionRequestContent content) {
        VerificationService service = new VerificationService();
        service.accept(content);
        return JSP_VERIFICATION_JSP;
    }
}
