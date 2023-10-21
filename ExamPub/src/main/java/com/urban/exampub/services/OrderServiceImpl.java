package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.BuyRequestDto;
import com.urban.exampub.models.DTOs.OrderResponseDto;
import com.urban.exampub.models.DTOs.OrderSummaryDto;
import com.urban.exampub.models.Drink;
import com.urban.exampub.models.ErrorResponse;
import com.urban.exampub.models.Order;
import com.urban.exampub.models.User;
import com.urban.exampub.repositories.DrinkRepository;
import com.urban.exampub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
  private final DrinkRepository drinkRepository;
  private final UserRepository userRepository;

  @Autowired
  public OrderServiceImpl(DrinkRepository drinkRepository, UserRepository userRepository) {
    this.drinkRepository = drinkRepository;
    this.userRepository = userRepository;
  }

  @Override
  public ResponseEntity<?> buyDrink(BuyRequestDto buyReq) {
    if (buyReq.getUserId() == null) {
      return ResponseEntity.status(400).body(new ErrorResponse("userId required field"));
    } else if (buyReq.getProductId() == null) {
      return ResponseEntity.status(400).body(new ErrorResponse("productId required"));
    } else if (buyReq.getPrice() == null) {
      return ResponseEntity.status(400)
          .body(
              new ErrorResponse(
                  "price required")); // ? price is already in product id, seems redundant to create
      // new one
    }
    Optional<User> user = userRepository.findById(buyReq.getUserId());
    if (user.isEmpty()) {
      return ResponseEntity.status(404)
          .body(new ErrorResponse("User with " + buyReq.getUserId() + " id not found"));
    }
    Optional<Drink> drink = drinkRepository.findById(buyReq.getProductId());
    if (drink.isEmpty()) {
      return ResponseEntity.status(404)
          .body(new ErrorResponse("Drink with " + buyReq.getProductId() + " id not found"));
    }
    if (user.get().getPocket() < buyReq.getPrice()) {
      return ResponseEntity.status(403)
          .body(new ErrorResponse("User doesn't have enough money in the pocket"));
    }
    if (drink.get().isForAdult() && !user.get().isAdult()) {
      return ResponseEntity.status(403)
          .body(new ErrorResponse("Drink is for adults and user is not an adult"));
    }
    return ResponseEntity.status(200).build();
  }

  @Override
  public ResponseEntity<List<OrderSummaryDto>> allOrdersByUsers() {
    List<User> userList = userRepository.findAll();
    List<OrderSummaryDto> response = userList.stream()
            .map(user -> {
              List<OrderResponseDto> orderResponseDtos = user.getOrders().stream()
                      .map(order -> new OrderResponseDto(order.getProductName(), order.getPrice()))
                      .collect(Collectors.toList());
              return new OrderSummaryDto(user.getId(), orderResponseDtos);
            })
            .collect(Collectors.toList());
    return ResponseEntity.status(200).body(response);
  }
}

