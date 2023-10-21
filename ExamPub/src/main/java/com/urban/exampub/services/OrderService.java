package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.BuyRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    ResponseEntity<?> buyDrink(BuyRequestDto buyReq);
}

