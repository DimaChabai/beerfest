package by.beerfest.repository.impl;

import by.beerfest.constant.Query;
import by.beerfest.entity.impl.Guest;
import by.beerfest.entity.TicketType;
import by.beerfest.repository.Repository;
import by.beerfest.repository.RepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.beerfest.constant.Query.*;

public class GuestRepository extends Repository {

    private static Logger logger = LogManager.getLogger();
    private static GuestRepository instance = new GuestRepository();

    public static GuestRepository getInstance() {
        return instance;
    }

    private GuestRepository() {
    }

    public void add(Guest guest) throws SQLException, RepositoryException {
        PreparedStatement statement = null;
        Connection conn = connectionPool.getConnection();
        try {
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(GUEST_INSERT);
            statement.setLong(1, guest.getId());
            statement.setInt(2, guest.getDefaultTicketNumber());
            statement.setInt(3, guest.getMediumTicketNumber());
            statement.setInt(4, guest.getLargeTicketNumber());
            statement.executeUpdate();

            statement = conn.prepareStatement(USER_TO_GUEST_UPDATE);
            statement.setLong(1, guest.getId());
            statement.executeUpdate();

            statement = conn.prepareStatement(ADD_TICKET);
            statement.setLong(1, guest.getId());
            statement.setInt(2, guest.getDefaultTicketNumber());
            statement.setString(3, TicketType.DEFAULT.toString());
            statement.execute();

            statement = conn.prepareStatement(ADD_TICKET);
            statement.setLong(1, guest.getId());
            statement.setInt(2, guest.getLargeTicketNumber());
            statement.setString(3, TicketType.LARGE.toString());
            statement.execute();

            statement = conn.prepareStatement(ADD_TICKET);
            statement.setLong(1, guest.getId());
            statement.setInt(2, guest.getMediumTicketNumber());
            statement.setString(3, TicketType.MEDIUM.toString());
            statement.execute();
        } catch (SQLException e) {
            conn.rollback();
            throw new RepositoryException(e);
        } finally {
            this.commit(conn);
            conn.setAutoCommit(true);
            this.closeStatement(statement);
        }
    }
}
