package by.beerfest.specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class FestSpecification {

    public FestSpecification() {

    }

    public abstract PreparedStatement specified(Connection connection) throws SQLException;
}
