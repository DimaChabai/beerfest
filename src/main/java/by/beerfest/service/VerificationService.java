package by.beerfest.service;

import by.beerfest.content.SessionRequestContent;
import by.beerfest.entity.Participant;

import java.sql.SQLException;
import java.util.List;

public interface VerificationService {
    void accept(long id) throws ServiceException;
    void decline(long id) throws SQLException, ServiceException;
    List<Participant> fillVerificationPage() throws ServiceException;
}
