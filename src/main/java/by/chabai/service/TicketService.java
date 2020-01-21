package by.chabai.service;

import by.chabai.content.SessionRequestContent;
import by.chabai.dto.TicketCountDTO;
import by.chabai.entity.Guest;
import by.chabai.entity.Place;
import by.chabai.entity.UserRole;
import by.chabai.pool.ConnectionPool;
import by.chabai.repository.GuestRepository;
import by.chabai.repository.PlaceRepository;
import by.chabai.repository.TicketRepository;
import by.chabai.specification.FestSpecification;
import by.chabai.specification.impl.FestSpecificationPlaceFindAllReserved;

import java.util.List;

import static by.chabai.constant.PageParameter.*;
import static by.chabai.constant.PagePath.JSP_MAIN_JSP;
import static by.chabai.constant.PagePath.JSP_TICKET_JSP;

public class TicketService {

    private static GuestRepository guestRepository = GuestRepository.getInstance();
    private static PlaceRepository placeRepository = PlaceRepository.getInstance();
    private static TicketRepository ticketRepository = TicketRepository.getInstance();
    private static ConnectionPool connectionPool = ConnectionPool.INSTANCE;//@TODO оставить или в метод перенести?

    public String addGuest(SessionRequestContent content) {
        String defaultTicketString = content.getRequestParameter(DEFAULT_TICKET_NUMBER);
        int defaultTicketCount = 0;
        if (!defaultTicketString.isBlank()) {
            defaultTicketCount = Integer.parseInt(defaultTicketString);
        }
        String mediumTicketString = content.getRequestParameter(MEDIUM_TICKET_NUMBER);
        int mediumTicketCount = 0;
        if (!mediumTicketString.isBlank()) {
            mediumTicketCount = Integer.parseInt(mediumTicketString);
        }
        String largeTicketString = content.getRequestParameter(LARGE_TICKET_NUMBER);
        int largeTicketCount = 0;
        if (!largeTicketString.isBlank()) {
            largeTicketCount = Integer.parseInt(largeTicketString);
        }


        if (defaultTicketCount == 0 && mediumTicketCount == 0 && largeTicketCount == 0) {
            content.setRequestAttribute(ERROR_MESSAGE, "Не выбрано ни одного билета");
            return JSP_TICKET_JSP;
        }
        content.setSessionAttribute(ROLE_NAME, UserRole.GUEST);
        Guest guest = new Guest();
        guest.setId((Long) content.getSessionAttribute(ID));
        guest.setDefaultTicketNumber(defaultTicketCount);
        guest.setMediumTicketNumber(mediumTicketCount);
        guest.setLargeTicketNumber(largeTicketCount);
        guestRepository.add(guest);
        return JSP_MAIN_JSP;
    }

    public void calculateTicketCount(SessionRequestContent content) {
        FestSpecification specification = new FestSpecificationPlaceFindAllReserved();
        List<Place> resultList = placeRepository.query(specification);
        TicketCountDTO ticketCountDTO = ticketRepository.calculateTicketCount(resultList);//@TODO мб лучше обойтись без dto и сразу в реквест вставлять данные
        content.setRequestAttribute(DEFAULT_TICKET_NUMBER, ticketCountDTO.getDefaultTicketCount());
        content.setRequestAttribute(MEDIUM_TICKET_NUMBER, ticketCountDTO.getMediumTicketCount());
        content.setRequestAttribute(LARGE_TICKET_NUMBER, ticketCountDTO.getLargeTicketCount());


    }

}
