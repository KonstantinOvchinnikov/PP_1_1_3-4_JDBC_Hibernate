package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    private static Util UTIL;
    private static final String URL = "jdbc:mysql://localhost:3306/lesson";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    public Util() {
    }

    public static Util getUTIL() {
        if (UTIL == null) {
            UTIL = new Util();
        }
        return UTIL;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
