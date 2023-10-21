package com.urban.exampub.services;

import com.urban.exampub.models.Drink;
import com.urban.exampub.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService{
    private final DrinkRepository drinkRepository;
    @Autowired
    public DrinkServiceImpl(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Override
    public ResponseEntity<List<Drink>> getAllDrinks() {
        return ResponseEntity.ok().body(drinkRepository.findAll());
    }
}
