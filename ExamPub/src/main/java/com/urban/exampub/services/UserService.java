package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.UserDto;
import com.urban.exampub.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserDto> getAllUserDto();
    public List<User> getAllUser();

}
