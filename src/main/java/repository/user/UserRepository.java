package repository.user;

import model.User;
import model.validation.Notification;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alex on 11/03/2017.
 */
public interface UserRepository {

    Notification<User> findByUsernameAndPassword(String username, String encodedPassword);

    boolean save(User user);

    boolean remove(User user);

    boolean update(User user);

    List<User> findAll();

    User findByUsername(String username) throws SQLException;

    void removeAll();

}
