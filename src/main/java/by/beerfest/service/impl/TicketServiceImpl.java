package by.beerfest.service.impl;

import by.beerfest.entity.PlaceType;
import by.beerfest.entity.impl.Guest;
import by.beerfest.entity.impl.Place;
import by.beerfest.repository.RepositoryException;
import by.beerfest.repository.impl.GuestRepository;
import by.beerfest.repository.impl.PlaceRepository;
import by.beerfest.repository.impl.TicketRepository;
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
        Map<String, Integer> ticketNumber = calculateTicketNumber();
        if ((defaultTicketCount == 0 && mediumTicketCount == 0 && largeTicketCount == 0)
                || ticketNumber.get(DEFAULT_TICKET_NUMBER) < defaultTicketCount
                || ticketNumber.get(MEDIUM_TICKET_NUMBER) < mediumTicketCount
                || ticketNumber.get(LARGE_TICKET_NUMBER) < largeTicketCount) {
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
            throw new ServiceException(e);
        }
        logger.info("User with id( " + id + ") booked tickets: default ticket: " + defaultTicketCount
                + ", medium ticket: " + mediumTicketCount
                + ", large ticket: " + largeTicketCount);
        return true;
    }

    public Map<String, Integer> calculateTicketNumber() throws ServiceException {
        FestSpecification specification = new FestSpecificationPlaceFindAllReserved();
        List<Place> reservedPlace;
        try {
            reservedPlace = placeRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        specification = new FestSpecificationTicketFindAllGroupByType();
        Map<String, Integer> bookedTicket;
        try {
            bookedTicket = ticketRepository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        int defaultTicketCount = reservedPlace.stream().filter(v -> v.getType().equals(PlaceType.SMALL)).reduce(0,
                (v, p) -> v + p.getSeats(),
                Integer::sum);
        int mediumTicketCount = reservedPlace.stream().filter(v -> v.getType().equals(PlaceType.MEDIUM)).reduce(0,
                (v, p) -> v + p.getSeats(),
                Integer::sum);
        int largeTicketCount = reservedPlace.stream().filter(v -> v.getType().equals(PlaceType.LARGE)).reduce(0,
                (v, p) -> v + p.getSeats(),
                Integer::sum);

        if (!bookedTicket.isEmpty()) {
            defaultTicketCount -= bookedTicket.get(BOOKED_DEFAULT_TICKET);
            mediumTicketCount -= bookedTicket.get(BOOKED_MEDIUM_TICKET);
            largeTicketCount -= bookedTicket.get(BOOKED_LARGE_TICKET);
        }
        Map<String, Integer> result = new HashMap<>();
        result.put(DEFAULT_TICKET_NUMBER, defaultTicketCount);
        result.put(MEDIUM_TICKET_NUMBER, mediumTicketCount);
        result.put(LARGE_TICKET_NUMBER, largeTicketCount);
        return result;
    }
}
