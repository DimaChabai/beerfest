package by.beerfest.pool;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;

public class ConnectionPoolTest {
    private ConnectionPool instance;

    @BeforeMethod
    public void setUp() {
        instance = ConnectionPool.INSTANCE;
    }

    @AfterMethod
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGetConnection() {
        Connection connection = instance.getConnection();
        Assert.assertEquals(ProxyConnection.class, connection.getClass());
    }
}