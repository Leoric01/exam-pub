package com.urban.exampub.services;

import com.urban.exampub.models.DTOs.summaryall.DrinkSummaryAll;
import com.urban.exampub.models.DTOs.summaryproduct.ProductAllOrdersDto;
import com.urban.exampub.models.Drink;
import com.urban.exampub.models.Order;
import com.urban.exampub.models.User;
import com.urban.exampub.repositories.DrinkRepository;
import com.urban.exampub.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DrinkServiceImplTest {

    @InjectMocks
    private DrinkServiceImpl drinkService;

    @Mock
    private DrinkRepository drinkRepository;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void getAllDrinks() {
        List<Drink> sampleDrinks = new ArrayList<>();
        sampleDrinks.add(new Drink("Cola", 20.2, false));
        sampleDrinks.add(new Drink("Wine", 55, true));
        when(drinkRepository.findAll()).thenReturn(sampleDrinks);
        ResponseEntity<List<Drink>> response = drinkService.getAllDrinks();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        List<Drink> returnedDrinks = response.getBody();
        assertNotNull(returnedDrinks);
        assertEquals(sampleDrinks.size(), returnedDrinks.size());
        assertTrue(returnedDrinks.containsAll(sampleDrinks));
    }

    @Test
    void summaryAll() {
        User user = new User();
        Order order1 = new Order("beer", 5, 50, user);
        List<Order> sampleOrders = List.of(order1);
        when(orderRepository.findAll()).thenReturn(sampleOrders);
        ResponseEntity<List<DrinkSummaryAll>> response = drinkService.summaryAll();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        List<DrinkSummaryAll> result = response.getBody();
        assertNotNull(result);
        for (DrinkSummaryAll summary : result) {
            if ("beer".equals(summary.getProduct())) {
                assertEquals(5, summary.getAmount());
                assertEquals(50, summary.getUnitPrice());
                assertEquals(250, summary.getSummaryPrice());
            }
        }
    }

    @Test
    void summaryAllProducts() {
        User user = new User();
        Order order1 = new Order("beer", 5, 50, user);
        Order order2 = new Order("cola", 1, 45, user);
        List<Order> sampleOrders = Arrays.asList(order1, order2);
        when(orderRepository.findAll()).thenReturn(sampleOrders);
        ResponseEntity<List<ProductAllOrdersDto>> response = drinkService.summaryAllProducts();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        List<ProductAllOrdersDto> result = response.getBody();
        assertNotNull(result);
    }

}