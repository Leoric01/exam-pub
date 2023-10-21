package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.DrinkSummaryAll;
import com.urban.exampub.models.Drink;
import com.urban.exampub.repositories.DrinkRepository;
import com.urban.exampub.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService{
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

        return null;
    }
}

