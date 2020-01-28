package by.beerfest.service;

import by.beerfest.content.SessionRequestContent;

import java.sql.SQLException;
import java.util.Map;

public interface TicketService {

    boolean addGuest(String defaultTicketString, String mediumTicketString, String largeTicketString,long id) throws SQLException, ServiceException;

    Map<String,Integer> calculateTicketNumber() throws ServiceException;
}
