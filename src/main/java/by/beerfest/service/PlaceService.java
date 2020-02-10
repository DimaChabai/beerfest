package by.beerfest.service;

/**
 * Interface for working with 'place' table.
 * Provides a method for creating a place.
 */
public interface PlaceService {

    void createPlace(String placeType, String seats) throws ServiceException;

}
