package by.chabai.service;

import by.chabai.content.SessionRequestContent;
import by.chabai.entity.Place;
import by.chabai.entity.PlaceType;
import by.chabai.repository.PlaceRepository;

import javax.servlet.http.HttpServletRequest;

import static by.chabai.constant.PageParameter.PLACE_TYPE;
import static by.chabai.constant.PageParameter.SEATS;

public class CreateService {

    private static PlaceRepository repository = PlaceRepository.getInstance();

    public void createPlace(SessionRequestContent content){
        Place place = new Place();
        place.setType(PlaceType.valueOf(content.getRequestParameter(PLACE_TYPE)));
        place.setSeats(Integer.parseInt(content.getRequestParameter(SEATS)));
        repository.add(place);
    }
}
