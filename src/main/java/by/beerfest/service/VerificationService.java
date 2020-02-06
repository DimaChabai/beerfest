package by.beerfest.service;

import by.beerfest.entity.impl.Participant;

import java.sql.SQLException;
import java.util.List;

public interface VerificationService {
    void accept(long id) throws ServiceException;
    void decline(long id) throws SQLException, ServiceException;
    List<Participant> getVerificationPageContent() throws ServiceException;
}
