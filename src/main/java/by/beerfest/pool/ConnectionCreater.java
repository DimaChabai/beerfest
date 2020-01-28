package by.beerfest.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ConnectionCreater {
    private static Logger logger = LogManager.getLogger();
    private static final String URL = "jdbc:mysql://localhost/beerfest?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    static BlockingQueue<ProxyConnection> initializePool(int INITIAL_POOL_SIZE) {
        BlockingQueue<ProxyConnection> connectionPool = new LinkedBlockingQueue<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {//@TODO бросать исключение дальше?
                connectionPool.offer(new ProxyConnection(ConnectionCreater.createConnection()));
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        if(connectionPool.size() != INITIAL_POOL_SIZE){
            throw new RuntimeException();
        }//@TODO замапить страницу с ошибкой runtime
        return connectionPool;
    }

    static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(ConnectionCreater.URL, ConnectionCreater.USERNAME, ConnectionCreater.PASSWORD);
    }
}
