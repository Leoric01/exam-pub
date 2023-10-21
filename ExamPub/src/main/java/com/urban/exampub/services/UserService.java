package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.UsersDto;
import com.urban.exampub.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UsersDto> getAllUserDto();
    List<User> getAllUser();

    ResponseEntity<?> getUserDetail(Long id);
}
