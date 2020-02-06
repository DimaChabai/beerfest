package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.constant.PageParameter;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.UserRole;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.ParticipantServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.beerfest.constant.PageMessage.*;
import static by.beerfest.constant.PageParameter.*;
import static by.beerfest.constant.PagePath.JSP_MAIN_JSP;

public class ParticipantCommand implements Command {

   private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        ParticipantServiceImpl service = new ParticipantServiceImpl();
        boolean result = false;
        try {
            String name = content.getRequestParameter(NAME)[0];
            String placeName = content.getRequestParameter(PLACE)[0];
            Long id = (Long) content.getSessionAttribute(ID);
            String beerType = content.getRequestParameter(BEERTYPE)[0];
            result = service.addParticipant(name, placeName, id, beerType);
            content.setRequestAttribute(MESSAGE, PARTICIPANT_MESSAGE);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE, PARTICIPANT_ERROR_MESSAGE);
        }
        if(result){
            content.setSessionAttribute(ROLE_NAME, UserRole.PARTICIPANT);
        } else {
            content.setRequestAttribute(ERROR_MESSAGE, INVALID_PARTICIPANT_DATA);
        }
        return JSP_MAIN_JSP;
    }
}
