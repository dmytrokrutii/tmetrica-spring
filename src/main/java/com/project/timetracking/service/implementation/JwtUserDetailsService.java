package com.project.timetracking.service.implementation;

import com.project.timetracking.model.User;
import com.project.timetracking.repository.UserRepository;
import com.project.timetracking.security.JwtUserFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    @NonNull
    private UserRepository userRepository;

    /**
     * Find user by username in database
     * Returns {@link com.project.timetracking.security.JwtUser} object
     *
     * @param s Username
     * @return Object of {@link com.project.timetracking.security.JwtUser}
     * @throws UsernameNotFoundException if no user find with specified username username
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", login));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
