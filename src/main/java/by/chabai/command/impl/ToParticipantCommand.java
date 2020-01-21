package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.content.SessionRequestContent;
import by.chabai.entity.Place;
import by.chabai.repository.PlaceRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationPlaceFindAllFree;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.chabai.constant.PageParameter.PLACES;
import static by.chabai.constant.PagePath.JSP_PARTICIPANT_JSP;

public class ToParticipantCommand implements Command {

    private static PlaceRepository repository = PlaceRepository.getInstance();

    @Override
    public String execute(SessionRequestContent content) {
        FestSpecification specification = new FestSpecificationPlaceFindAllFree();//@TODO Надо ли это в сервис выносить?
        List<Place> resultList = repository.query(specification);
        content.setRequestAttribute(PLACES, resultList);
        return JSP_PARTICIPANT_JSP;
    }
}
