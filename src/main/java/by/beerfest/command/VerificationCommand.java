package by.beerfest.command;

import by.beerfest.controller.SessionRequestContent;
import by.beerfest.entity.impl.Participant;
import by.beerfest.service.ParticipantService;
import by.beerfest.service.ServiceException;
import by.beerfest.service.impl.ParticipantServiceImpl;

import java.util.List;

import static by.beerfest.command.PageParameter.PARTICIPANTS;

public abstract class VerificationCommand implements Command {

    private ParticipantService service = new ParticipantServiceImpl();

    /**
     * Passes an array of participants to the request.
     *
     * @param content object which contain request, response and session information
     * @throws ServiceException {@code Exception} which can be thrown by {@code VerificationServiceImpl}
     */

    protected void fillPage(SessionRequestContent content) throws ServiceException {
        List<Participant> result = service.getVerificationPageContent();
        content.setRequestAttribute(PARTICIPANTS, result);
    }
}
