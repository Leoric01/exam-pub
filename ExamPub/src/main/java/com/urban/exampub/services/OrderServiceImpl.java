package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.BuyRequestCreateDto;
import com.urban.exampub.models.DTOs.BuyRequestDto;
import com.urban.exampub.models.DTOs.summaryuser.OrderResponseDto;
import com.urban.exampub.models.DTOs.summaryuser.OrderSummaryDto;
import com.urban.exampub.models.Drink;
import com.urban.exampub.models.ErrorResponse;
import com.urban.exampub.models.Order;
import com.urban.exampub.models.User;
import com.urban.exampub.repositories.DrinkRepository;
import com.urban.exampub.repositories.OrderRepository;
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
  private final OrderRepository orderRepo;

  @Autowired
  public OrderServiceImpl(DrinkRepository drinkRepository, UserRepository userRepository, OrderRepository orderRepo) {
    this.drinkRepository = drinkRepository;
    this.userRepository = userRepository;
    this.orderRepo = orderRepo;
  }

  @Override
  public ResponseEntity<?> validateBuyDrink(BuyRequestDto buyReq) {
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
    List<OrderSummaryDto> response = userRepository.findAll().stream()
            .map(user -> {
              OrderSummaryDto orderSummaryDto = new OrderSummaryDto();
              orderSummaryDto.setUserId(user.getId());

              List<OrderResponseDto> orderResponseDtos = user.getOrders().stream()
                      .map(order -> {
                        OrderResponseDto orderResponseDto = new OrderResponseDto();
                        orderResponseDto.setPrice(order.getPrice());
                        orderResponseDto.setProduct(order.getProductName());
                        return orderResponseDto;
                      })
                      .collect(Collectors.toList());

              orderSummaryDto.setOrderResponseDtos(orderResponseDtos);
              return orderSummaryDto;
            })
            .collect(Collectors.toList());
    return ResponseEntity.status(200).body(response);
  }


  @Override
  public ResponseEntity<?> createOrderDrink(BuyRequestCreateDto buyReq) {
    if (buyReq.getUserId() == null) {
      return ResponseEntity.status(400).body(new ErrorResponse("userId required field"));
    } else if (buyReq.getProductId() == null) {
      return ResponseEntity.status(400).body(new ErrorResponse("productId required"));
    } else if (buyReq.getPrice() == null) {
      return ResponseEntity.status(400).body(new ErrorResponse("price required"));
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
    Order order = new Order(drink.get().getProductName(), buyReq.getAmount(), buyReq.getPrice(), user.get());
    orderRepo.save(order);
    return ResponseEntity.status(201).build();
  }
}
