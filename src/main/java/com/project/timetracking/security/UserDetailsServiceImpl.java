package com.project.timetracking.security;

import com.project.timetracking.domain.entity.User;
import com.project.timetracking.domain.enums.Role;
import com.project.timetracking.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Custom implementation of {@link UserDetailsService}.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @NonNull
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(login);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", login));
        } else {
            return new UserDetailsImpl(user);
        }
    }

    /**
     * Has admin authority boolean.
     *
     * @param userDetails the user details
     * @return the boolean
     */
    public static boolean hasAdminAuthority(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals(Role.ADMIN.getAuthority()));
    }
}
