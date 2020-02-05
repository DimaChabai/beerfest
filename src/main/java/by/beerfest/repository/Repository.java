package by.beerfest.repository;

import by.beerfest.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Repository {
    protected static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    private static Logger logger = LogManager.getLogger();

    public void closeStatement(Statement statement)  {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    public void commit(Connection connection)  {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
