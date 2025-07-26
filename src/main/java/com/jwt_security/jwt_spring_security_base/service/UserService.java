package com.jwt_security.jwt_spring_security_base.service;

import com.jwt_security.jwt_spring_security_base.dto.request.LoginDTO;
import com.jwt_security.jwt_spring_security_base.dto.request.RegisterDTO;
import com.jwt_security.jwt_spring_security_base.dto.response.UserDTO;
import com.jwt_security.jwt_spring_security_base.entity.User;
import com.jwt_security.jwt_spring_security_base.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public UserDTO registerUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        user.setRole(registerDTO.getRole());
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new UserDTO(user.getUsername(), token);
    }

    public UserDTO verifyUser(LoginDTO loginDTO) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                ));

        if (authentication.isAuthenticated()) {
            User user = userRepository.findByUsername(loginDTO.getUsername());
            String token = jwtService.generateToken(user);
            return new UserDTO(loginDTO.getUsername(), token);
        }

        throw new AuthenticationCredentialsNotFoundException("Authentication Failed");
    }
}
