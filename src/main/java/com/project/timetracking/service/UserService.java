package com.project.timetracking.service;

import com.project.timetracking.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void create(User user);

    User find(long id);

    User findByEmail(String email);

    User findByLogin(String login);

    void update(User user);

    void delete(long id);

    boolean existByEmail(String email);

    boolean existByLogin(String login);

}
