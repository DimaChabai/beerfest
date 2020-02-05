package by.beerfest.service.impl;

import by.beerfest.entity.Participant;
import by.beerfest.repository.impl.ParticipantRepository;
import by.beerfest.repository.RepositoryException;
import by.beerfest.service.ServiceException;
import by.beerfest.service.VerificationService;
import by.beerfest.specification.FestSpecification;
import by.beerfest.specification.impl.FestSpecificationParticipantFindByConfirmedIsFalse;
import by.beerfest.specification.impl.FestSpecificationParticipantFindById;

import java.sql.SQLException;
import java.util.List;

public class VerificationServiceImpl implements VerificationService {

    private static ParticipantRepository participantRepository = ParticipantRepository.getInstance();

    public void accept(long id) throws ServiceException {
        FestSpecification specification = new FestSpecificationParticipantFindById(id);
        Participant participant;
        try {
            participant = participantRepository.query(specification).get(0);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        participant.setConfirmed(true);
        try {
            participantRepository.update(participant);
        } catch (RepositoryException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public void decline(long id) throws ServiceException {
        FestSpecification specification = new FestSpecificationParticipantFindById(id);
        Participant participant;
        try {
            participant = participantRepository.query(specification).get(0);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        try {
            participantRepository.delete(participant);
        } catch (RepositoryException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    public List<Participant> fillVerificationPage() throws ServiceException {
        FestSpecification specification = new FestSpecificationParticipantFindByConfirmedIsFalse();
        List<Participant> resultList;
        try {
            resultList = participantRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return resultList;
    }
}
