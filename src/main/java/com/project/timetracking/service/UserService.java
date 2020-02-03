package com.project.timetracking.service;

import com.project.timetracking.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The interface User service.
 */
@Service
public interface UserService {
    /**
     * Create boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean create(User user);

    /**
     * Find user.
     *
     * @param id the id
     * @return the user
     */
    User find(long id);

    /**
     * Find by email user.
     *
     * @param email the email
     * @return the user
     */
    User findByEmail(String email);

    /**
     * Update.
     *
     * @param user the user
     */
    void update(User user);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<User> getAll();

}
