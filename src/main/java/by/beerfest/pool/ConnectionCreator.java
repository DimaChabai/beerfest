package by.beerfest.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ConnectionCreator {
    public static final String BUNDLE = "database";
    public static final String POOL_SIZE = "db.pool_size";
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_DRIVER = "db.driver";
    private static Logger logger = LogManager.getLogger();

    private ConnectionCreator() {
    }//@TODO private конструктор и дефолтный модификатор

    static BlockingQueue<ProxyConnection> initializePool(final int INITIAL_POOL_SIZE) {
        ResourceBundle resource = ResourceBundle.getBundle(BUNDLE);
        String driver = resource.getString(DB_DRIVER);
        int poolSize;
        if (resource.containsKey(POOL_SIZE)) {
            poolSize = Integer.parseInt(resource.getString(POOL_SIZE));
        } else {
            poolSize = INITIAL_POOL_SIZE;
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error(e);
            throw new ExceptionInInitializerError(e);
        }
        BlockingQueue<ProxyConnection> connectionPool = new LinkedBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            try {
                connectionPool.offer(new ProxyConnection(ConnectionCreator.createConnection()));
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        if (connectionPool.size() != poolSize) {
            throw new ExceptionInInitializerError();
        }
        return connectionPool;
    }

    static Connection createConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle(BUNDLE);
        String url = resource.getString(DB_URL);
        String username = resource.getString(DB_USERNAME);
        String password = resource.getString(DB_PASSWORD);
        return DriverManager.getConnection(url, username, password);
    }
}
