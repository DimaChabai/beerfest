package by.beerfest.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public enum ConnectionPool {
    INSTANCE;

    private static Logger logger;
    private BlockingQueue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> usedConnections;
    private final int INITIAL_POOL_SIZE = 10;


    ConnectionPool() {
        init();

    }

    private void init() {
        logger = LogManager.getLogger();
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            logger.error(e); //@TODO надо прокинуть исключение дальше?
        }
        usedConnections = new ArrayDeque<>();
        availableConnections = ConnectionCreater.initializePool(INITIAL_POOL_SIZE);
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {//@TODO бросать исключение дальше?
            connection = availableConnections.take();
            usedConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            availableConnections.offer((ProxyConnection) connection);
            usedConnections.remove(connection);
        } else {
            throw new IllegalArgumentException("Release Not ProxyConnection");//@TODO бросать исключение дальше?
        }
    }

    public void shutdown() {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {//@TODO бросать исключение дальше?
                availableConnections.take().reallyClose();
            } catch (InterruptedException | SQLException e) {
                logger.error(e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver1 -> {
            try {//@TODO бросать исключение дальше?
                DriverManager.deregisterDriver(driver1);
            } catch (SQLException e) {
                logger.error(e);
            }
        });
    }

    public int getSize() {
        return availableConnections.size() + usedConnections.size();
    }
}
