package com.jwt_security.jwt_spring_security_base.controller;

import com.jwt_security.jwt_spring_security_base.dto.request.LoginDTO;
import com.jwt_security.jwt_spring_security_base.dto.request.RegisterDTO;
import com.jwt_security.jwt_spring_security_base.dto.response.UserDTO;
import com.jwt_security.jwt_spring_security_base.entity.User;
import com.jwt_security.jwt_spring_security_base.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterDTO registerDTO) {
        UserDTO userDto = userService.registerUser(registerDTO);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        UserDTO userDTO = userService.verifyUser(loginDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
