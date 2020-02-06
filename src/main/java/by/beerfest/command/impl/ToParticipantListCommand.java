package by.beerfest.command.impl;

import by.beerfest.command.Command;
import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.impl.Participant;
import by.beerfest.service.ParticipantService;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.ParticipantServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.beerfest.constant.PageParameter.*;
import static by.beerfest.constant.PagePath.JSP_MAIN_JSP;
import static by.beerfest.constant.PagePath.JSP_PARTICIPANT_LIST_JSP;

public class ToParticipantListCommand implements Command {

    public static final String LOAD_ERROR_MESSAGE = "page.message.participant_load_error_message";
    private static Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent content) {
        ParticipantService service = new ParticipantServiceImpl();
        try {
            String[] s = content.getRequestParameter(PAGE);
            int page = 1;
            if (s != null) {
                page = Integer.parseInt(s[0]);
                if(page<1){
                    page = 1;
                }
            }
            content.setRequestAttribute(PAGE,page);
            List<Participant> participants = service.getParticipantsFromTo((page-1) * 4, page  * 4);
            content.setRequestAttribute(PARTICIPANTS, participants);
        } catch (ServiceException e) {
            logger.error(e);
            content.setRequestAttribute(ERROR_MESSAGE, LOAD_ERROR_MESSAGE);
        }
        return JSP_PARTICIPANT_LIST_JSP;
    }
}
