package com.project.timetracking.model.service.implementation;

import com.project.timetracking.model.domain.entity.User;
import com.project.timetracking.model.domain.enums.Role;
import com.project.timetracking.model.repository.UserRepository;
import com.project.timetracking.model.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * The type User service.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository  the user repository
     * @param passwordEncoder the password encoder
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean create(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            log.debug("user with email '{}' is already exist, cant register", user.getEmail());
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        log.debug("user added - {}", user);
        return true;
    }

    @Override
    public User find(long id) {
        log.info("find user by id {}", id);
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        log.info("Find user by email {}", email);
        return userRepository.findByEmail(email);
    }

}
