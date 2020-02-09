package by.beerfest.specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Realisation of Specification pattern.
 */
public interface FestSpecification {

    PreparedStatement specified(Connection connection) throws SQLException;

}
