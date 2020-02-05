package com.project.timetracking.controller;

import com.project.timetracking.domain.entity.User;
import com.project.timetracking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The type Auth controller.
 */
@Controller
public class AuthController {
    private final UserService userService;
    private final static String REGISTRATION_PAGE = "registration";
    private final static String LOGIN_PAGE = "login";

    /**
     * Instantiates a new Auth controller.
     *
     * @param userService the user service
     */
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Login string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = "/login")
    public String login(Model model) {
        return LOGIN_PAGE;
    }

    /**
     * Registration string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        return REGISTRATION_PAGE;
    }

    /**
     * Add user string.
     *
     * @param user  the user
     * @param model the model
     * @return the string
     */
    @PostMapping(value = "/registration")
    public String addUser(User user, Model model) {
        if (userService.create(user)) {
            return "redirect:/login";
        }
        return REGISTRATION_PAGE;
    }

}
