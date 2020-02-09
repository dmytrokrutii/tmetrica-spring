package com.project.timetracking.service;

import com.project.timetracking.domain.entity.User;
import com.project.timetracking.repository.UserRepository;
import com.project.timetracking.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The type User service impl test.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void createUserReturnTrueIfUserAddedToDataBase() {
        User user = new User();
        user.setEmail("email@test");
        user.setPassword("pass");

        Mockito.doReturn(false)
                .when(userRepository)
                .existsByEmail("email@test");

        Mockito.doReturn("$2a$12$sTY0xD4hWM46am15nCv.oOz121VAyhAz.9VV.DxqXKZ97rCQmWzUq")
                .when(passwordEncoder)
                .encode("pass");

        boolean isUserCreated = userService.create(user);
        Assert.assertTrue(isUserCreated);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);

        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(ArgumentMatchers.anyString());
        Mockito.verify(userRepository, Mockito.times(1)).existsByEmail(ArgumentMatchers.anyString());
    }

    @Test
    void createUserReturnFalseIfUserExistInDataBase() {
        User user = new User();
        user.setEmail("solosuicide133@gmail.com");

        Mockito.doReturn(true)
                .when(userRepository)
                .existsByEmail("solosuicide133@gmail.com");

        boolean isUserCreated = userService.create(user);
        Assert.assertFalse(isUserCreated);


        Mockito.verify(userRepository, Mockito.times(1)).existsByEmail(ArgumentMatchers.anyString());

        Mockito.verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));

    }

}
