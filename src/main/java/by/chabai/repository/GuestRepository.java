package by.chabai.repository;

import by.chabai.entity.Guest;
import by.chabai.entity.TicketType;
import by.chabai.pool.ProxyConnection;
import by.chabai.service.StatementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.chabai.constant.Query.*;

public class GuestRepository extends Repository {

    private static Logger logger = LogManager.getLogger();
    private static GuestRepository instance = new GuestRepository();

    public static GuestRepository getInstance() {
        return instance;
    }

    private GuestRepository() {
    }

    public void add(Guest guest) {


        PreparedStatement statement = null;
        Connection conn = connectionPool.getConnection();
        try {
            conn.setAutoCommit(false);//@TODO
            statement = conn.prepareStatement(GUEST_INSERT);
            statement.setLong(1, guest.getId());
            statement.setInt(2,guest.getDefaultTicketNumber());
            statement.setInt(3,guest.getMediumTicketNumber());
            statement.setInt(4,guest.getLargeTicketNumber());
            statement.executeUpdate();
            statement.close();
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
           logger.warn(e.getMessage());
            ((ProxyConnection) conn).rollback();
        } finally {
            StatementService.commit(conn);
            ((ProxyConnection) conn).setAutoCommit(true);//@TODO каст, что бы не обрабатывать эксепшн
            StatementService.closeStatement(statement);
        }
    }
}
