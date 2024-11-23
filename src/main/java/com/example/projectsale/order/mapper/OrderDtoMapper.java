package com.example.projectsale.order.mapper;

import com.example.projectsale.order.dto.OrderDto;
import com.example.projectsale.order.entity.Order;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class OrderDtoMapper implements Function<Order, OrderDto> {
    @Override
    public OrderDto apply(Order order) {
        return OrderDto.builder()
                .email(order.getEmail())
                .address(order.getAddress())
                .build();
    }
}
