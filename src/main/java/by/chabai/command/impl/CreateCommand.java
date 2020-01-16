package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.entity.Place;
import by.chabai.entity.PlaceType;
import by.chabai.repository.PlaceRepository;

import javax.servlet.http.HttpServletRequest;

import static by.chabai.constant.PageParameter.PLACE_TYPE;
import static by.chabai.constant.PageParameter.SEATS;
import static by.chabai.constant.PagePath.JSP_CREATE_JSP;

public class CreateCommand implements Command {


    private static PlaceRepository repository = PlaceRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        Place place = new Place();
        place.setType(PlaceType.valueOf(request.getParameter(PLACE_TYPE)));//@TODO
        place.setSeats(Integer.parseInt(request.getParameter(SEATS)));
        repository.add(place);
        return JSP_CREATE_JSP;
    }
}
