package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.dto.OrderRequestDto;
import com.ecommerce.order_service.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrdersService ordersService;

    @GetMapping
    public ResponseEntity<?> getAllOrder() {
        log.info("fetching all orders via controller");
        List<OrderRequestDto> allOrders = ordersService.getAllOrders();
        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        log.info("Fetching order with ID {} :", id);
        OrderRequestDto order = ordersService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
