package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS lesson.users(" +
            "id INT NOT NULL AUTO_INCREMENT," +
            "name VARCHAR(45) NOT NULL," +
            "lastName VARCHAR(45) NOT NULL," +
            "age INT(3) NOT NULL," +
            "PRIMARY KEY(id))" +
            "DEFAULT CHARACTER SET = utf8";
    String DROP_TABLE = "DROP TABLE IF EXISTS lesson.users";

    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
