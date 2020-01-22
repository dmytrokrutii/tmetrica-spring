package com.project.timetracking.contoller;

import com.project.timetracking.dto.SingUpForm;
import com.project.timetracking.model.User;
import com.project.timetracking.security.JwtAuthenticationRequest;
import com.project.timetracking.security.JwtTokenUtil;
import com.project.timetracking.security.JwtUser;
import com.project.timetracking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.token.header}")
    private String tokenHeader;

    @Value("${jwt.token.prefix}")
    private String tokenPrefix;

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;

    private UserDetailsService userDetailsService;

    private UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                          @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> createAuthenticationToken
            (@RequestBody JwtAuthenticationRequest authenticationRequest) {

        authenticate(authenticationRequest.getLogin(), authenticationRequest.getPassword());
        final JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(authenticationRequest.getLogin());
        final String token = jwtTokenUtil.generateToken(userDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.add(tokenHeader, tokenPrefix + token);
        logger.info("User {} successfully authenticated", authenticationRequest.getLogin());

        return new ResponseEntity(headers, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SingUpForm singUpForm) {
        if (userService.existByEmail(singUpForm.getEmail())) {
            return new ResponseEntity("Fail -> Email is already in use!", HttpStatus.BAD_REQUEST);
        }
        if (userService.existByEmail(singUpForm.getEmail())) {
            return new ResponseEntity("Fail -> Login is already in use!", HttpStatus.BAD_REQUEST);
        }
        User userToSignUp = new User(singUpForm.getEmail(), singUpForm.getLogin(), passwordEncoder.encode(singUpForm.getPassword()));
        userService.create(userToSignUp);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
