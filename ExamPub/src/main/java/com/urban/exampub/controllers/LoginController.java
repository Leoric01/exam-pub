package com.urban.exampub.controllers;

import com.urban.exampub.models.DTOs.UserRequestDto;
import com.urban.exampub.models.UserLoginRequest;
import com.urban.exampub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest login){
        return null;
    }
}
