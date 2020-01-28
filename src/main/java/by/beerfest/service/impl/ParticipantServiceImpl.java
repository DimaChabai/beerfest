package by.beerfest.service.impl;

import by.beerfest.entity.Participant;
import by.beerfest.entity.Place;
import by.beerfest.repository.ParticipantRepository;
import by.beerfest.repository.PlaceRepository;
import by.beerfest.repository.RepositoryException;
import by.beerfest.service.ParticipantService;
import by.beerfest.service.ServiceException;
import by.beerfest.specification.FestSpecification;
import by.beerfest.specification.impl.FestSpecificationPlaceFindAllFree;
import by.beerfest.specification.impl.FestSpecificationPlaceFindById;
import by.beerfest.validator.ParticipantDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParticipantServiceImpl implements ParticipantService {

    private static final ParticipantRepository participantRepository = ParticipantRepository.getInstance();
    private static final PlaceRepository placeRepository = PlaceRepository.getInstance();
    private static final String ID_PLACE_REGEX = "№\\d*";
    private static Logger logger = LogManager.getLogger();

    public boolean addParticipant(String name, String placeName, Long id) throws ServiceException {

        ParticipantDataValidator validator = new ParticipantDataValidator();

        if(!validator.nameValidate(name)){
            return false;
        }

        Participant participant = new Participant();
        Pattern pattern = Pattern.compile(ID_PLACE_REGEX);
        Matcher matcher = pattern.matcher(placeName);
        String idPlace = "";
        if (matcher.find()) {
            idPlace = matcher.group().substring(1);
        } else {
            logger.error("Impossible state");
        }
        FestSpecification specification = new FestSpecificationPlaceFindById(Long.parseLong(idPlace));
        Place place;
        try {
            place = placeRepository.query(specification).get(0);
        } catch (RepositoryException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        participant.setPlace(place);
        participant.setConfirmed(false);
        participant.setName(name);
        participant.setId(id);
        try {
            participantRepository.add(participant);
            logger.info("Participant registered: " + participant);
        } catch (RepositoryException | SQLException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return true;
    }

    public List<Place> getPlaces() throws ServiceException {
        //@TODO тут тоже нужен контент
        FestSpecification specification = new FestSpecificationPlaceFindAllFree();
        List<Place> resultList = null;
        try {
            resultList = placeRepository.query(specification);
        } catch (RepositoryException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return resultList;
    }
}
