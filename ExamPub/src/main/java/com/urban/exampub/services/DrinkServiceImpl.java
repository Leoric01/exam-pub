package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.DrinkSummaryAll;
import com.urban.exampub.models.DTOs.ProductSpecificSummary;
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
      if (drinkSummaryMap.containsKey(productName)) {
        DrinkSummaryAll existingSummary = drinkSummaryMap.get(productName);
        existingSummary.setAmount(existingSummary.getAmount() + order.getAmount());
        existingSummary.setSummaryPrice(
            existingSummary.getSummaryPrice() + (order.getAmount() * order.getPrice()));
      } else {
        DrinkSummaryAll drinkSummary = new DrinkSummaryAll();
        drinkSummary.setProduct(productName);
        drinkSummary.setAmount(order.getAmount());
        drinkSummary.setUnitPrice(order.getPrice());
        drinkSummary.setSummaryPrice(order.getAmount() * order.getPrice());
        drinkSummaryMap.put(productName, drinkSummary);
      }
    }
    List<DrinkSummaryAll> allDrinks = new ArrayList<>(drinkSummaryMap.values());
    return ResponseEntity.ok(allDrinks);
  }

  @Override
  public ResponseEntity<?> summarySpecificProduct(String product) {
    if(!orderRepository.existsByProductName(product)){
      return ResponseEntity.status(404).body(new ErrorResponse("Product with this name doesn't exist"));
    }
    List<Order> orderList = orderRepository.findAll();
    List<ProductSpecificSummary> productOrdersList = new ArrayList<>();
    for (Order order : orderList){
      if (order.getProductName().equals(product)){
        ProductSpecificSummary productSpecificSummary = new ProductSpecificSummary(order.getId(), order.getAmount(), order.getPrice());
        productOrdersList.add(productSpecificSummary);
      }
    }
    return ResponseEntity.ok().body(productOrdersList);
  }
}
