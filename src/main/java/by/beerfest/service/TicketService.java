package by.beerfest.service;

import java.util.Map;

/**
 * Interface for working with 'guest_ticket' table.
 * Provides a method for adding an entry that associates a guest with their tickets.
 */
public interface TicketService {

    boolean addGuest(String defaultTicketString, String mediumTicketString, String largeTicketString, long id) throws ServiceException;

    Map<String, Integer> calculateTicketNumber() throws ServiceException;
}
