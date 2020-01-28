package by.beerfest.service;

import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.Place;

import java.sql.SQLException;
import java.util.List;

public interface ParticipantService {
    boolean addParticipant(String name, String placeName, Long id) throws SQLException, ServiceException;
    List<Place> getPlaces() throws ServiceException;
}
