package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Queue;

public class UserDaoHibernateImpl implements UserDao {
    private final Util util = Util.getUTIL();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute(DROP_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            transaction.commit();
            System.out.printf("User с именем — %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            transaction.commit();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = util.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
