package com.project.timetracking.service.implementation;

import com.project.timetracking.model.User;
import com.project.timetracking.repository.UserRepository;
import com.project.timetracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public User find(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        System.out.println(userRepository.findByEmail(email));
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existByLogin(String login) {
        return userRepository.existsByLogin(login);
    }
}
