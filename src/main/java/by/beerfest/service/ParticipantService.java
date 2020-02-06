package by.beerfest.service;

import by.beerfest.entity.impl.Participant;
import by.beerfest.entity.impl.Place;

import java.sql.SQLException;
import java.util.List;

public interface ParticipantService {
    boolean addParticipant(String name, String placeName, Long id,String beerType) throws SQLException, ServiceException;
    List<Place> getPlaces() throws ServiceException;
    List<Participant> getParticipantsFromTo(int start,int end) throws ServiceException;
}
