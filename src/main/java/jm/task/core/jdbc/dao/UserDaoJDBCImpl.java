package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao, AutoCloseable {
    Util util = Util.getUTIL();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS lesson.users(" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(45) NOT NULL," +
                "lastName VARCHAR(45) NOT NULL," +
                "age INT(3) NOT NULL," +
                "PRIMARY KEY(id))" +
                "DEFAULT CHARACTER SET = utf8";

        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS lesson.users";
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO lesson.users(name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.execute();
            System.out.printf("User с именем — %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM lesson.users WHERE id = ?";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM lesson.users";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)) {
            ResultSet a = preparedStatement.executeQuery();
            while (a.next()) {
                User user = new User();
                user.setId(a.getLong("id"));
                user.setName(a.getString("name"));
                user.setLastName(a.getString("lastName"));
                user.setAge(a.getByte("age"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {

        String sql = "DELETE FROM lesson.users";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }
}
