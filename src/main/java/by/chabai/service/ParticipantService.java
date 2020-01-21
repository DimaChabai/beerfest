package by.chabai.service;

import by.chabai.content.SessionRequestContent;
import by.chabai.entity.Participant;
import by.chabai.entity.Place;
import by.chabai.entity.UserRole;
import by.chabai.repository.ParticipantRepository;
import by.chabai.repository.PlaceRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationPlaceFindById;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.chabai.constant.PageParameter.*;

public class ParticipantService {

    private static final ParticipantRepository participantRepository = ParticipantRepository.getInstance();
    private static final PlaceRepository placeRepository = PlaceRepository.getInstance();
    private static final String ID_PLACE_REGEX = "№\\d";


    public void addParticipant(SessionRequestContent content) {
        String name = content.getRequestParameter(NAME);
        String placeName = content.getRequestParameter(PLACE);
        Participant participant = new Participant();

        Pattern pattern = Pattern.compile(ID_PLACE_REGEX);//@TODO
        Matcher matcher = pattern.matcher(placeName);
        String idPlace = "";
        if (matcher.find()) {
            idPlace = matcher.group().substring(1);
        }else{
            //@TODO logger невозможный эксепшн;
        }
        FestSpecification specification = new FestSpecificationPlaceFindById(Long.parseLong(idPlace));
        Place place = placeRepository.query(specification).get(0);
        participant.setPlace(place);
        participant.setConfirmed(false);
        participant.setName(name);
        participant.setId((Long) content.getSessionAttribute(ID));
        content.setSessionAttribute(ROLE_NAME, UserRole.PARTICIPANT);
        participantRepository.add(participant);
    }
}
