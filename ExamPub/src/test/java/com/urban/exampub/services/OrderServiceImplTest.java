package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.BuyRequestDto;
import com.urban.exampub.models.DTOs.summaryuser.OrderSummaryDto;
import com.urban.exampub.models.Drink;
import com.urban.exampub.models.Order;
import com.urban.exampub.models.User;
import com.urban.exampub.repositories.DrinkRepository;
import com.urban.exampub.repositories.OrderRepository;
import com.urban.exampub.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DrinkRepository drinkRepository;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void validateBuyDrink() {
        BuyRequestDto validBuyRequest = new BuyRequestDto(1L, 2L, 10.0);
        User sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setAdult(true);
        sampleUser.setPocket(500);
        Drink sampleDrink = new Drink("cola", 20, false);

        when(userRepository.findById(validBuyRequest.getUserId())).thenReturn(Optional.of(sampleUser));
        when(drinkRepository.findById(validBuyRequest.getProductId())).thenReturn(Optional.of(sampleDrink));

        ResponseEntity<?> response = orderService.validateBuyDrink(validBuyRequest);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        BuyRequestDto nullUserIdRequest = new BuyRequestDto(null, 2L, 10.0);
        ResponseEntity<?> responseNullUserId = orderService.validateBuyDrink(nullUserIdRequest);
        assertEquals(400, responseNullUserId.getStatusCodeValue());

      }

    @Test
    void allOrdersByUsers() {
      }
}