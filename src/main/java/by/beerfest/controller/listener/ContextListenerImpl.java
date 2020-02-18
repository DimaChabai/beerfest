package by.beerfest.controller.listener;

import by.beerfest.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Initializing connection pool
 */
@WebListener
public class ContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;//pool initialize
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.INSTANCE.shutdown();
    }
}
