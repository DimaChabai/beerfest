package by.beerfest.service.impl;

import by.beerfest.entity.impl.Place;
import by.beerfest.entity.PlaceType;
import by.beerfest.repository.impl.PlaceRepository;
import by.beerfest.repository.RepositoryException;
import by.beerfest.service.CreateService;
import by.beerfest.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateServiceImpl implements CreateService {

    private static Logger logger = LogManager.getLogger();
    private static PlaceRepository repository = PlaceRepository.getInstance();

    public void createPlace(String placeType, String seats) throws ServiceException {
        Place place = new Place();
        place.setType(PlaceType.valueOf(placeType));
        place.setSeats(Integer.parseInt(seats));
        try {
            repository.add(place);
            logger.info("Place(" + place + ") created");
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
