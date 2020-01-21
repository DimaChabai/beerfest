package by.chabai.service;

import by.chabai.content.SessionRequestContent;
import by.chabai.entity.Participant;
import by.chabai.repository.ParticipantRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationParticipantFindById;

import javax.servlet.http.HttpServletRequest;

import static by.chabai.constant.PageParameter.ID;

public class VerificationService {

    private static ParticipantRepository participantRepository = ParticipantRepository.getInstance();

    public void accept(SessionRequestContent content){
        long id = Long.parseLong(content.getRequestParameter(ID));
        FestSpecification specification = new FestSpecificationParticipantFindById(id);
        Participant participant = participantRepository.query(specification).get(0);
        participant.setConfirmed(true);
        participantRepository.update(participant);
    }

    public void decline(SessionRequestContent content){
        long id = Long.parseLong(content.getRequestParameter(ID));
        FestSpecification specification = new FestSpecificationParticipantFindById(id);
        Participant participant = participantRepository.query(specification).get(0);
        participantRepository.delete(participant);
    }
}
