package jm.task.core.jdbc.util;

import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

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

    public SessionFactory getSessionFactory() throws SQLException {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        properties.put(Environment.URL, URL);
        properties.put(Environment.USER, USERNAME);
        properties.put(Environment.PASS, PASSWORD);
//        properties.put(Environment.SHOW_SQL, true);


        return new Configuration().setProperties(properties).addAnnotatedClass(User.class).buildSessionFactory();
    }
}
