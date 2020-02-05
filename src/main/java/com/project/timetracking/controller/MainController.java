package com.project.timetracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Main controller.
 */
@Controller
public class MainController {
    private static final String HOME_PAGE = "home";

    /**
     * Welcome string.
     *
     * @return the string
     */
    @GetMapping(value = "/")
    public String welcome() {
        return HOME_PAGE;
    }

    /**
     * Home string.
     *
     * @return the string
     */
    @GetMapping(value = "/home")
    public String home() {
        return HOME_PAGE;
    }

}
