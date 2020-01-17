package org.tickets.germes.app.service;

import java.util.List;
import java.util.Optional;
import org.tickets.germes.app.model.entity.person.User;

/**
 * Provides API for the user management
 */
public interface UserService {
    /**
     * Saves(creates or modifies) specified user instance
     */
    void save(User user);

    /**
     * Returns user with specified identifier boxed into Optional
     */
    Optional<User> findById(int userId);

    /**
     * Delete city with specified identifier
     */
    void delete(int userId);

    /**
     * Returns all the cities
     */
    List<User> findAll();

}
