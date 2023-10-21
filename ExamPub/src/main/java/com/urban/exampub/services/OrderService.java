package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.BuyRequestDto;
import com.urban.exampub.models.DTOs.OrderSummaryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    ResponseEntity<?> buyDrink(BuyRequestDto buyReq);

    ResponseEntity<List<OrderSummaryDto>> allOrdersByUsers();
}

