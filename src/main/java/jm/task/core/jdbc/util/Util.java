package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/lesson";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    protected static Connection getConnection() throws SQLException{
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);

    }
}
