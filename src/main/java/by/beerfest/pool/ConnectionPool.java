package by.beerfest.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public enum ConnectionPool {
    INSTANCE;

    private final int DEFAULT_POOL_SIZE = 10;
    private Logger logger;
    private BlockingQueue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> usedConnections;

    ConnectionPool() {
        init();
    }

    private void init() {
        logger = LogManager.getLogger();
        usedConnections = new ArrayDeque<>();
        availableConnections = ConnectionCreator.initializePool(DEFAULT_POOL_SIZE);
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = availableConnections.take();
            usedConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            availableConnections.offer((ProxyConnection) connection);//todo put
            usedConnections.remove(connection);
        } else {
            Thread.currentThread().interrupt();
            logger.error("Release not ProxyConnection");
            throw new RuntimeException();
        }
    }

    public void shutdown() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                availableConnections.take().reallyClose();
            } catch (InterruptedException | SQLException e) {
                logger.error(e);
                throw new ExceptionInInitializerError(e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error(e);
                throw new ExceptionInInitializerError(e);
            }
        });
    }

    public int getSize() {
        return availableConnections.size() + usedConnections.size();
    }
}
