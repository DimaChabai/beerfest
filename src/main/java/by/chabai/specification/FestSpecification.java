package by.chabai.specification;

import by.chabai.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class FestSpecification {
    protected Connection connection;

    public FestSpecification(Connection connection) {
        this.connection = connection;
    }

    public abstract PreparedStatement specified() throws SQLException;
}
