package com.urban.exampub.controllers;

import com.urban.exampub.models.DTOs.UsersDto;
import com.urban.exampub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {
  private final UserService userService;

  @Autowired
  public UsersController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public ResponseEntity<List<UsersDto>> listUsers() {
    return ResponseEntity.ok(userService.getAllUserDto());
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<?> userDetail(@PathVariable Long id) {
    return userService.getUserDetail(id);
  }
}
