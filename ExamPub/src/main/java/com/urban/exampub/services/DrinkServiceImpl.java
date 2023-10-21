package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.DrinkRequestDto;
import com.urban.exampub.models.DTOs.DrinkResponseDto;
import com.urban.exampub.models.DTOs.OrderDto;
import com.urban.exampub.models.DTOs.summaryall.DrinkSummaryAll;
import com.urban.exampub.models.DTOs.summaryproduct.ProductAllOrdersDto;
import com.urban.exampub.models.DTOs.summaryproduct.ProductSpecificSummary;
import com.urban.exampub.models.Drink;
import com.urban.exampub.models.ErrorResponse;
import com.urban.exampub.models.Order;
import com.urban.exampub.repositories.DrinkRepository;
import com.urban.exampub.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DrinkServiceImpl implements DrinkService {
  private final DrinkRepository drinkRepository;
  private final OrderRepository orderRepository;

  @Autowired
  public DrinkServiceImpl(DrinkRepository drinkRepository, OrderRepository orderRepository) {
    this.drinkRepository = drinkRepository;
    this.orderRepository = orderRepository;
  }

  @Override
  public ResponseEntity<List<Drink>> getAllDrinks() {
    return ResponseEntity.ok().body(drinkRepository.findAll());
  }

  @Override
  public ResponseEntity<List<DrinkSummaryAll>> summaryAll() {
    List<Order> orderList = orderRepository.findAll();
    Map<String, DrinkSummaryAll> drinkSummaryMap = new HashMap<>();
    for (Order order : orderList) {
      String productName = order.getProductName();
      OrderDto orderDto =
          new OrderDto(order.getId(), order.getProductName(), order.getAmount(), order.getPrice());
      if (drinkSummaryMap.containsKey(productName)) {
        DrinkSummaryAll existingSummary = drinkSummaryMap.get(productName);
        existingSummary.setAmount(existingSummary.getAmount() + order.getAmount());
        existingSummary.setSummaryPrice(
            existingSummary.getSummaryPrice() + (order.getAmount() * order.getPrice()));
        existingSummary.getOrders().add(orderDto);
      } else {
        List<OrderDto> orderDtos = new ArrayList<>();
        DrinkSummaryAll drinkSummary = new DrinkSummaryAll();
        drinkSummary.setProduct(productName);
        drinkSummary.setAmount(order.getAmount());
        drinkSummary.setUnitPrice(order.getPrice());
        drinkSummary.setSummaryPrice(order.getAmount() * order.getPrice());
        drinkSummaryMap.put(productName, drinkSummary);
        drinkSummary.setOrders(orderDtos);
        drinkSummary.getOrders().add(orderDto);
      }
    }
    List<DrinkSummaryAll> allDrinks = new ArrayList<>(drinkSummaryMap.values());
    return ResponseEntity.ok(allDrinks);
  }

  @Override
  public ResponseEntity<List<ProductAllOrdersDto>> summaryAllProducts() {
    List<Order> orderList = orderRepository.findAll();
    Map<String, List<Order>> ordersByProduct =
        orderList.stream().collect(Collectors.groupingBy(Order::getProductName));
    List<ProductAllOrdersDto> productAllOrdersDtos = new ArrayList<>();
    for (Map.Entry<String, List<Order>> entry : ordersByProduct.entrySet()) {
      String productName = entry.getKey();
      List<Order> productOrders = entry.getValue();
      List<ProductSpecificSummary> productSpecificSummaries =
          productOrders.stream()
              .map(
                  order ->
                      new ProductSpecificSummary(
                          order.getUser().getId(), order.getAmount(), order.getPrice()))
              .collect(Collectors.toList());
      ProductAllOrdersDto productDto =
          new ProductAllOrdersDto(productName, productSpecificSummaries);
      productAllOrdersDtos.add(productDto);
    }
    return ResponseEntity.ok().body(productAllOrdersDtos);
  }

  @Override
  public ResponseEntity<?> createDrink(DrinkRequestDto drinkRequestDto) {
    if (drinkRequestDto.getName() == null || drinkRequestDto.getName().isBlank()) {
      return ResponseEntity.status(400).body(new ErrorResponse("name required"));
    }
    if (drinkRequestDto.getIsForAdult() == null){
      return ResponseEntity.status(400).body(new ErrorResponse("isForAdult required"));
    }
    if (drinkRequestDto.getPrice() == null){
      return ResponseEntity.status(400).body(new ErrorResponse("price required"));
    }
    Drink drink = new Drink();
    drink.setProductName(drinkRequestDto.getName());
    drink.setPrice(drinkRequestDto.getPrice());
    drink.setForAdult(drinkRequestDto.getIsForAdult());
    drinkRepository.save(drink);
    DrinkResponseDto responseDto = new DrinkResponseDto(drink.getId(), drinkRequestDto.getName(), drinkRequestDto.getPrice(), drinkRequestDto.getIsForAdult());
    return ResponseEntity.status(201).body(responseDto);
  }
}
