package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.*;
import com.urban.exampub.models.ErrorResponse;
import com.urban.exampub.models.User;
import com.urban.exampub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<UsersDto> getAllUserDto() {
    List<User> users = getAllUser();
    return users.stream()
        .map(
            user ->
                new UsersDto(
                    user.getId(),
                    user.getName(),
                    user.isActive(),
                    user.isAdult(),
                    user.getPocket()))
        .collect(Collectors.toList());
  }

  @Override
  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  @Override
  public ResponseEntity<?> getUserDetail(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      List<OrderDto> orderDtos =
          user.get().getOrders().stream()
              .map(
                  order ->
                      new OrderDto(
                          order.getId(),
                          order.getProductName(),
                          order.getAmount(),
                          order.getPrice()))
              .collect(Collectors.toList());
      UserOrderDto userorderdto =
          new UserOrderDto(
              user.get().getId(),
              user.get().getName(),
              user.get().isActive(),
              user.get().isAdult(),
              user.get().getPocket(),
              orderDtos);
      return ResponseEntity.ok().body(userorderdto);
    }
    return ResponseEntity.status(404)
        .body(new ErrorResponse("user with " + id + " id doesn't exist"));
  }

  @Override
  public ResponseEntity<?> createUser(UserRequestDto userRequestDto) {
    if (userRequestDto.getName() == null || userRequestDto.getName().isBlank()){
      return ResponseEntity.status(400).body(new ErrorResponse("name is required"));
    }
    if (userRequestDto.getIsAdult() == null){
      return ResponseEntity.status(400).body(new ErrorResponse("isAdult is required"));
    }
    if (userRequestDto.getPocket() == null){
      return ResponseEntity.status(400).body(new ErrorResponse("pocket is required"));
    }
    User user = new User();
    user.setName(userRequestDto.getName());
    user.setAdult(userRequestDto.getIsAdult());
    user.setPocket(userRequestDto.getPocket());
    userRepository.save(user);
    return ResponseEntity.status(201).body(new UserDto(user.getId(), user.getName(), user.isAdult(), user.getPocket()));
  }
}
