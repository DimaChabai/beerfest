package by.chabai.command.impl;

import by.chabai.command.Command;
import by.chabai.entity.Guest;
import by.chabai.entity.UserRole;
import by.chabai.pool.ConnectionPool;
import by.chabai.repository.GuestRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.chabai.constant.PageParameter.*;
import static by.chabai.constant.PagePath.JSP_MAIN_JSP;
import static by.chabai.constant.PagePath.JSP_TICKET_JSP;

public class TicketCommand implements Command {

    private static ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static GuestRepository guestRepository = GuestRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        int defaultTicketCount = Integer.parseInt(request.getParameter(DEFAULT_TICKET_NUMBER));
        int mediumTicketCount = Integer.parseInt(request.getParameter(MEDIUM_TICKET_NUMBER));
        int largeTicketCount = Integer.parseInt(request.getParameter(LARGE_TICKET_NUMBER));

        Guest guest = new Guest();
        HttpSession session = request.getSession();
        guest.setId((Long) session.getAttribute(ID));
        session.setAttribute(ROLE_NAME, UserRole.GUEST.toString());

        guest.setDefaultTicketNumber(defaultTicketCount);
        guest.setMediumTicketNumber(mediumTicketCount);
        guest.setLargeTicketNumber(largeTicketCount);
        guestRepository.add(guest);
        return JSP_MAIN_JSP;
    }
}
