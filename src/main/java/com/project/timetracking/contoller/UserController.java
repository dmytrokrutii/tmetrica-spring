package com.project.timetracking.contoller;

import com.project.timetracking.model.User;
import com.project.timetracking.service.UserService;
import com.project.timetracking.wrapper.GeneralResponseWrapper;
import com.project.timetracking.wrapper.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponseWrapper<User> getByEMail(@PathVariable String email) {
        return new GeneralResponseWrapper(Status.of(HttpStatus.OK), userService.findByEmail(email));
    }

    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponseWrapper<User> getByLogin(@PathVariable String email) {
        return new GeneralResponseWrapper(Status.of(HttpStatus.OK), userService.findByLogin(email));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GeneralResponseWrapper<User> getById(@PathVariable long id) {
        return new GeneralResponseWrapper(Status.of(HttpStatus.OK), userService.find(id));
    }

}
