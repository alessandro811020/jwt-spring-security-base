package com.jwt_security.jwt_spring_security_base.service;

import com.jwt_security.jwt_spring_security_base.entity.User;
import com.jwt_security.jwt_spring_security_base.model.UserAuthDetails;
import com.jwt_security.jwt_spring_security_base.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (null == user) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return new UserAuthDetails(user);
    }
}
