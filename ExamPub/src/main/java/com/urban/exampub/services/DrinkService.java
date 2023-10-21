package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.DrinkSummaryAll;
import com.urban.exampub.models.Drink;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DrinkService {
    ResponseEntity<List<Drink>> getAllDrinks();

    ResponseEntity<List<DrinkSummaryAll>> summaryAll();
}

