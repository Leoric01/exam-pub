package com.urban.exampub.controllers;

import com.urban.exampub.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final DrinkService drinkService;
    @Autowired
    public ProductController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }
}
