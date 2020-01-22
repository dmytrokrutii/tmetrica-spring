package com.project.timetracking.repository;

import com.project.timetracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String username);

    User findByEmail(String email);

    User findByLoginOrEmail(String login, String email);

    User findById(long id);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

}
