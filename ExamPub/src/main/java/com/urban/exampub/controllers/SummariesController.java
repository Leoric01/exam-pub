package com.urban.exampub.controllers;

import com.urban.exampub.models.DTOs.DrinkSummaryAll;
import com.urban.exampub.services.DrinkService;
import com.urban.exampub.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/summary")
public class SummariesController {
    private final OrderService orderService;
    private final DrinkService drinkService;

    @Autowired
    public SummariesController(OrderService orderService, DrinkService drinkService) {
        this.orderService = orderService;
        this.drinkService = drinkService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DrinkSummaryAll>> summaryAll(){
       return drinkService.summaryAll();
    }
    @GetMapping("/{product}")
    public ResponseEntity<?> summarySpecificProduct(@PathVariable String product){
        return drinkService.summarySpecificProduct(product);
    }
    @GetMapping("/user")
    public ResponseEntity<?> summaryUsersOrders(){
        return orderService.allOrdersByUsers();
    }
}
