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
                .orderId(order.getId())
                .email(order.getEmail())
                .address(order.getAddress())
                .fullName(order.getFullName())
                .orderDate(order.getOrderDate())
                .statusOrder(order.getStatusOrder() != null ? order.getStatusOrder().name():null)
                .phoneNumber(order.getPhoneNumber())
                .description(order.getDescription())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
