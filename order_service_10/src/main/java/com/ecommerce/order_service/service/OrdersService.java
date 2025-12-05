package com.ecommerce.order_service.service;

import com.ecommerce.order_service.dto.OrderRequestDto;
import com.ecommerce.order_service.dto.OrderRequestItemDto;
import com.ecommerce.order_service.entity.Orders;
import com.ecommerce.order_service.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {
    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;

    public List<OrderRequestDto> getAllOrders() {
        log.info("fetching all orders");

        List<Orders> orders = orderRepo.findAll();

        return orders.stream().map(order -> {
            OrderRequestDto dto = new OrderRequestDto();
            dto.setId(order.getId());
            dto.setTotalPrice(
                    order.getTotalPrice() == null ? null :
                            BigDecimal.valueOf(order.getTotalPrice())
            );

            // Map items
            List<OrderRequestItemDto> items = order.getOrderItemList()
                    .stream()
                    .map(item -> {
                        OrderRequestItemDto itemDto = new OrderRequestItemDto();
                        itemDto.setId(item.getId());
                        itemDto.setProductId(item.getProductId());
                        itemDto.setQuantity(item.getQuantity());
                        return itemDto;
                    })
                    .collect(Collectors.toList());

            dto.setItems(items);
            return dto;

        }).collect(Collectors.toList());
    }

    public OrderRequestDto getOrderById(Long id) {
        log.info("fetching order with order id {} : ", id);

        Orders order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderRequestDto dto = new OrderRequestDto();
        dto.setId(order.getId());

        dto.setTotalPrice(order.getTotalPrice() == null
                ? null
                : BigDecimal.valueOf(order.getTotalPrice()));

        // Map order items
        List<OrderRequestItemDto> items = order.getOrderItemList()
                .stream()
                .map(item -> {
                    OrderRequestItemDto itemDto = new OrderRequestItemDto();
                    itemDto.setId(item.getId());
                    itemDto.setProductId(item.getProductId());
                    itemDto.setQuantity(item.getQuantity());
                    return itemDto;
                })
                .collect(Collectors.toList());

        dto.setItems(items);
        return dto;
    }

}
