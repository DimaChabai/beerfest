package by.beerfest.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ConnectionCreater {
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";
    private static Logger logger = LogManager.getLogger();

    static BlockingQueue<ProxyConnection> initializePool(final int INITIAL_POOL_SIZE) {
        BlockingQueue<ProxyConnection> connectionPool = new LinkedBlockingQueue<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                connectionPool.offer(new ProxyConnection(ConnectionCreater.createConnection()));
            } catch (SQLException e) {
                logger.error(e);
                throw new ExceptionInInitializerError(e);
            }
        }
        if (connectionPool.size() != INITIAL_POOL_SIZE) {
            throw new ExceptionInInitializerError();
        }
        return connectionPool;
    }

    static Connection createConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString(DB_URL);
        String username = resource.getString(DB_USERNAME);
        String password = resource.getString(DB_PASSWORD);
        return DriverManager.getConnection(url, username, password);
    }
}
