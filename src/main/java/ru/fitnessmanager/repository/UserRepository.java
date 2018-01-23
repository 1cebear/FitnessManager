package ru.fitnessmanager.repository;

import ru.fitnessmanager.model.User;
import java.util.List;

/**
 * Created by Icebear on 22.07.2017.
 */
public interface UserRepository {

    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();
}
