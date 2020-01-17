package org.tickets.germes.app.service.impl;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.tickets.germes.app.infra.cdi.DBSource;
import org.tickets.germes.app.infra.util.SecurityUtil;
import org.tickets.germes.app.model.entity.person.User;
import org.tickets.germes.app.persistance.UserRepository;
import org.tickets.germes.app.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Inject
    public UserServiceImpl(@DBSource UserRepository userRepository) {
        this.userRepository = userRepository;

        User user = new User();
        user.setUserName("guest");
        user.setPassword(SecurityUtil.encryptSHA("guest"));
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void delete(int userId) {
        userRepository.delete(userId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}