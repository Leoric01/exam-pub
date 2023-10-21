package com.urban.exampub.controllers;

import com.urban.exampub.models.DTOs.BuyRequestDto;
import com.urban.exampub.models.Drink;
import com.urban.exampub.services.DrinkService;
import com.urban.exampub.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final DrinkService drinkService;
    private final OrderService orderService;
    @Autowired
    public ProductController(DrinkService drinkService, OrderService orderService) {
        this.drinkService = drinkService;
        this.orderService = orderService;
    }

    @GetMapping("/drink-menu")
    public ResponseEntity<List<Drink>> drinkMenu(){
        return drinkService.getAllDrinks();
    }
    @PostMapping("/buy")
    public ResponseEntity<?> buyDrink(@RequestBody BuyRequestDto buyReq){
        return orderService.buyDrink(buyReq);
    }
}
