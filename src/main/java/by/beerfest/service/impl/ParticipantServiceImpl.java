package by.beerfest.service.impl;

import by.beerfest.entity.impl.Participant;
import by.beerfest.entity.impl.Place;
import by.beerfest.repository.RepositoryException;
import by.beerfest.repository.impl.BeerRepository;
import by.beerfest.repository.impl.ParticipantRepository;
import by.beerfest.repository.impl.PlaceRepository;
import by.beerfest.service.ParticipantService;
import by.beerfest.service.ServiceException;
import by.beerfest.specification.FestSpecification;
import by.beerfest.specification.impl.FestSpecificationBeerFindAll;
import by.beerfest.specification.impl.FestSpecificationParticipantFindByConfirmedIsTrue;
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

    private static final BeerRepository beerRepository = BeerRepository.getInstance();
    private static final ParticipantRepository participantRepository = ParticipantRepository.getInstance();
    private static final PlaceRepository placeRepository = PlaceRepository.getInstance();
    private static final String ID_PLACE_REGEX = "№\\d*";
    private static Logger logger = LogManager.getLogger();

    public boolean addParticipant(String name, String placeName, Long id, String beerType) throws ServiceException {
        List<String> beers = getBeers();
        ParticipantDataValidator validator = new ParticipantDataValidator();
        if (!validator.companyNameValidate(name) || !beers.contains(beerType)) {
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
            throw new ServiceException(e);
        }
        participant.setPlace(place);
        participant.setConfirmed(false);
        participant.setName(name);
        participant.setId(id);
        participant.setBeerType(beerType);
        try {
            participantRepository.add(participant);
            logger.info(String.format("Participant registered: %s", participant));
        } catch (RepositoryException | SQLException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    public List<String> getBeers() throws ServiceException {
        FestSpecification specification = new FestSpecificationBeerFindAll();
        List<String> beers;
        try {
            beers = beerRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return beers;
    }

    public List<Place> getPlaces() throws ServiceException {
        List<Place> resultList;
        FestSpecification specification = new FestSpecificationPlaceFindAllFree();
        try {
            resultList = placeRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return resultList;
    }

    @Override
    public List<Participant> getParticipantsFromTo(int start, int end) throws ServiceException {
        List<Participant> participants;
        FestSpecification specification = new FestSpecificationParticipantFindByConfirmedIsTrue(start, end);
        try {
            participants = participantRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return participants;
    }
}
