package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.UserDto;
import com.urban.exampub.models.User;
import com.urban.exampub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserDto> getAllUserDto() {
        List<User> users = getAllUser();
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.isActive(), user.isAdult(), user.getPocket()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
