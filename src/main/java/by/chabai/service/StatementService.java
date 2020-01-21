package by.chabai.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementService {

    private static Logger logger = LogManager.getLogger();

    public static void closeStatement(Statement statement){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
               logger.warn(e.getMessage());
            }
        }
    }
    public static void commit(Connection connection){
        try {
            connection.commit();//@TODO
        } catch (SQLException e) {
           logger.warn(e.getMessage());
        }
    }
}
