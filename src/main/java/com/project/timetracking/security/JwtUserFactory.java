package com.project.timetracking.security;

import com.project.timetracking.model.User;
import com.project.timetracking.model.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getLogin(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.isEnabled(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole())
        );
    }

    private static ArrayList<GrantedAuthority> mapToGrantedAuthorities(Role role) {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }

}
