package org.tickets.germes.app.persistance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.tickets.germes.app.model.entity.person.User;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(int userId);
    void delete(int userId);
    List<User> findAll();
    Integer[] test1 = new Integer[10];


}
