package by.chabai.specification;

import by.chabai.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class FestSpecification {

    public FestSpecification() {

    }

    public abstract PreparedStatement specified(Connection connection) throws SQLException;
}
