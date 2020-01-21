package by.chabai.pool;

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

    private static Logger logger = LogManager.getLogger();
    private BlockingQueue<ProxyConnection> connectionPool;
    private Queue<ProxyConnection> usedConnections;
    private final int INITIAL_POOL_SIZE = 10;
    private Driver driver;

    ConnectionPool() {
        try {
            driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            //logger.warn(e.getMessage()); @TODO не писать тут лог т.к. надо прокинуть  исключение дальше?
        }
        usedConnections = new ArrayDeque<>();
        connectionPool = ConnectionCreater.initializePool(INITIAL_POOL_SIZE);
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = connectionPool.take();
            usedConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.warn(e.getMessage());
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            connectionPool.offer((ProxyConnection) connection);
            usedConnections.remove(connection);
        } else {
            throw new IllegalArgumentException("Освобождение не ProxyConnection");//@TODO правильно ли
        }
    }

    public void shutdown() {
        usedConnections.forEach(this::releaseConnection);//@TODO Правильно?
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                connectionPool.take().reallyClose();
            } catch (InterruptedException e) {
                logger.warn(e.getMessage());
            }
        }
        connectionPool.clear();//@TODO Правильно?
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver1 -> {
            try {
                DriverManager.deregisterDriver(driver1);
            } catch (SQLException e) {
                logger.warn(e.getMessage());
            }
        });//@TODO зачем объект драйвера?
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
}
