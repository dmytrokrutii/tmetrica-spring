package com.project.timetracking.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

/**
 * Custom implementation of {@link UserDetails}. Provides core user information.
 */
@Getter
@Setter
public class JwtUser implements UserDetails {
    private final Long id;
    private final String email;
    private final String login;
    private final String password;
    private final String name;
    private final String surname;
    private final boolean enabled;
    private final ArrayList<? extends GrantedAuthority> authorities;

    public JwtUser(
            Long id,
            String login,
            String name,
            String surname,
            String email,
            boolean enabled,
            String password,
            ArrayList<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public ArrayList<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return login;
    }


}
