package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.content.SessionRequestContent;
import by.chabai.entity.Participant;
import by.chabai.repository.ParticipantRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationParticipantFindByConfirmedIsFalse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.chabai.constant.PageParameter.PARTICIPANTS;
import static by.chabai.constant.PagePath.JSP_VERIFICATION_JSP;

public class ToVerificationCommand implements Command {

    ParticipantRepository participantRepository = ParticipantRepository.getInstance();

    @Override
    public String execute(SessionRequestContent content) {
        FestSpecification specification = new FestSpecificationParticipantFindByConfirmedIsFalse();//@TODO Надо ли это в сервис выносить?
        List<Participant> resultList = participantRepository.query(specification);
        content.setRequestAttribute(PARTICIPANTS, resultList);
        return JSP_VERIFICATION_JSP;
    }
}
