package by.beerfest.service.impl;

import by.beerfest.entity.Guest;
import by.beerfest.entity.Place;
import by.beerfest.entity.PlaceType;
import by.beerfest.repository.GuestRepository;
import by.beerfest.repository.PlaceRepository;
import by.beerfest.repository.RepositoryException;
import by.beerfest.repository.TicketRepository;
import by.beerfest.service.ServiceException;
import by.beerfest.service.TicketService;
import by.beerfest.specification.FestSpecification;
import by.beerfest.specification.impl.FestSpecificationPlaceFindAllReserved;
import by.beerfest.specification.impl.FestSpecificationTicketFindAllGroupByType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.beerfest.constant.PageParameter.*;

public class TicketServiceImpl implements TicketService {

    private static Logger logger = LogManager.getLogger();
    private static GuestRepository guestRepository = GuestRepository.getInstance();
    private static PlaceRepository placeRepository = PlaceRepository.getInstance();
    private static TicketRepository ticketRepository = TicketRepository.getInstance();

    public boolean addGuest(String defaultTicketString, String mediumTicketString, String largeTicketString, long id) throws ServiceException {

        int defaultTicketCount = 0;
        if (!defaultTicketString.isBlank()) {
            defaultTicketCount = Integer.parseInt(defaultTicketString);
        }
        int mediumTicketCount = 0;
        if (!mediumTicketString.isBlank()) {
            mediumTicketCount = Integer.parseInt(mediumTicketString);
        }
        int largeTicketCount = 0;
        if (!largeTicketString.isBlank()) {
            largeTicketCount = Integer.parseInt(largeTicketString);
        }


        if (defaultTicketCount == 0 && mediumTicketCount == 0 && largeTicketCount == 0) {
            return false;
        }
        Guest guest = new Guest();
        guest.setId(id);
        guest.setDefaultTicketNumber(defaultTicketCount);
        guest.setMediumTicketNumber(mediumTicketCount);
        guest.setLargeTicketNumber(largeTicketCount);
        try {
            guestRepository.add(guest);
        } catch (RepositoryException | SQLException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        logger.info("User(id: " + id + ") booked tickets: default ticket: " + defaultTicketCount
                + ", medium ticket: " + mediumTicketCount
                + ", large ticket: " + largeTicketCount);//@TODO форматирование
        return true;
    }

    public Map<String, Integer> calculateTicketNumber() throws ServiceException {
        FestSpecification specification = new FestSpecificationPlaceFindAllReserved();
        List<Place> reservedPlace;
        try {
            reservedPlace = placeRepository.query(specification);
        } catch (RepositoryException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        specification = new FestSpecificationTicketFindAllGroupByType();
        Map<String, Integer> bookedTicket;
        try {
            bookedTicket = ticketRepository.query(specification);
        } catch (RepositoryException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        int defaultTicketCount = 0;
        int mediumTicketCount = 0;
        int largeTicketCount = 0;

        if(!bookedTicket.isEmpty()){
            defaultTicketCount = reservedPlace.stream().filter((v) -> v.getType().equals(PlaceType.SMALL)).reduce(0,
                    (v, p) -> v + p.getSeats(),
                    Integer::sum) - bookedTicket.get(BOOKED_DEFAULT_TICKET);
            mediumTicketCount = reservedPlace.stream().filter((v) -> v.getType().equals(PlaceType.MEDIUM)).reduce(0,
                    (v, p) -> v + p.getSeats(),
                    Integer::sum) - bookedTicket.get(BOOKED_MEDIUM_TICKET);

            largeTicketCount = reservedPlace.stream().filter((v) -> v.getType().equals(PlaceType.LARGE)).reduce(0,
                    (v, p) -> v + p.getSeats(),
                    Integer::sum) - bookedTicket.get(BOOKED_LARGE_TICKET);
        }
        Map<String, Integer> result = new HashMap<>();//@TODO вовращаю мапу
        result.put(DEFAULT_TICKET_NUMBER, defaultTicketCount);
        result.put(MEDIUM_TICKET_NUMBER, mediumTicketCount);
        result.put(LARGE_TICKET_NUMBER, largeTicketCount);
        return result;
    }
}
