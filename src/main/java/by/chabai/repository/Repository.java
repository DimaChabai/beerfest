package by.chabai.repository;

import by.chabai.pool.ConnectionPool;

public abstract class Repository {
    protected static final ConnectionPool connectionPool = ConnectionPool.INSTANCE;
    protected static final String URL = "jdbc:mysql://localhost/beerfest?serverTimezone=Europe/Moscow&useSSL=false";
    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "12345";
}
