package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.constant.PageParameter;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.UserRole;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.ParticipantServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageParameter.ERROR_MESSAGE;
import static by.beerfest.constant.PagePath.JSP_MAIN_JSP;

public class ParticipantCommand implements Command {

    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        ParticipantServiceImpl service = new ParticipantServiceImpl();
        boolean result = false;
        try {
            result = service.addParticipant(content.getRequestParameter(PageParameter.NAME), content.getRequestParameter(PageParameter.PLACE), (Long) content.getSessionAttribute(PageParameter.ID));
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE, "page.message.participant_error_message");
        }
        if(result){
            content.setSessionAttribute(PageParameter.ROLE_NAME, UserRole.PARTICIPANT);
        } else {
            content.setRequestAttribute(ERROR_MESSAGE,"page.message.invalid_participant_data");
        }
        return JSP_MAIN_JSP;
    }
}
