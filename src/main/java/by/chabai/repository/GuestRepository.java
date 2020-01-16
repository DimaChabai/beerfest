package by.chabai.repository;

import by.chabai.entity.Guest;
import by.chabai.entity.TicketType;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static by.chabai.constant.Query.*;

public class GuestRepository extends Repository {

    private static GuestRepository instance = new GuestRepository();

    public static GuestRepository getInstance() {
        return instance;
    }

    private GuestRepository() {
    }

    public void add(Guest guest) {
        Connection conn = connectionPool.getConnection();

        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(GUEST_INSERT);
            statement.setLong(1, guest.getId());
            statement.executeUpdate();
            statement.close();
            statement = conn.prepareStatement(USER_TO_GUEST_UPDATE);
            statement.setLong(1, guest.getId());
            statement.executeUpdate();
            statement.close();

            statement = conn.prepareStatement(ADD_TICKET);
            statement.setLong(1,guest.getId());
            statement.setInt(2,guest.getDefaultTicketNumber());
            statement.setString(3, TicketType.DEFAULT.toString());
            statement.execute();

            statement = conn.prepareStatement(ADD_TICKET);
            statement.setLong(1,guest.getId());
            statement.setInt(2,guest.getLargeTicketNumber());
            statement.setString(3, TicketType.LARGE.toString());
            statement.execute();

            statement = conn.prepareStatement(ADD_TICKET);
            statement.setLong(1,guest.getId());
            statement.setInt(2,guest.getMediumTicketNumber());
            statement.setString(3, TicketType.MEDIUM.toString());
            statement.execute();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.releaseConnection(conn);
        }
    }
}
