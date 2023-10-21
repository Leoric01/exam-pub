package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.BuyRequestDto;
import com.urban.exampub.models.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public ResponseEntity<?> buyDrink(BuyRequestDto buyReq) {
        if (buyReq.getUserId() == null){
            return ResponseEntity.status(400).body(new ErrorResponse("userId required field"));
        }
        else if (buyReq.getProductId() == null){
            return ResponseEntity.status(400).body(new ErrorResponse("productId required"));
        }
        else if(buyReq.getPrice() == null){
            return ResponseEntity.status(400).body(new ErrorResponse("price required")); // ? price is already in product id, seems redundant to create new one
        }
        return null;
    }
}
