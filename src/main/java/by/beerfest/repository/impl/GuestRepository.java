package by.beerfest.repository.impl;

import by.beerfest.entity.impl.Guest;
import by.beerfest.repository.Repository;
import by.beerfest.repository.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.beerfest.specification.Query.GUEST_INSERT;
import static by.beerfest.specification.Query.USER_TO_GUEST_UPDATE;

/**
 * Realization of {@code Repository} interface.
 * It is singleton.
 * Used to access the table 'guest'.
 */
public class GuestRepository extends Repository {

    private static GuestRepository instance = new GuestRepository();

    private GuestRepository() {
    }

    public static GuestRepository getInstance() {
        return instance;
    }

    /**
     * Add a guest to the table 'guest'.
     *
     * @param guest object that contains information about guest
     * @throws SQLException        if a database access error occurs,
     *                             setAutoCommit(true) is called while participating in a distributed transaction,
     *                             or this method is called on a closed connection. Can be thrown from finally block.
     * @throws RepositoryException wraps SQLException
     */
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
        } catch (SQLException e) {
            conn.rollback();
            throw new RepositoryException(e);
        } finally {
            this.commit(conn);
            this.closeStatement(statement);
            conn.setAutoCommit(true);
            conn.close();
        }
    }
}
