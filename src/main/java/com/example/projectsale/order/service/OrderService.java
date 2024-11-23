package com.example.projectsale.order.service;

import com.example.projectsale.order.dto.OrderDto;
import com.example.projectsale.order.entity.Order;

import java.util.List;

public interface OrderService {

    void demo(String search);


    List<OrderDto> searchOrders(String search);

    OrderDto createOrder(Order order);

    OrderDto updateOrder(String orderId, Order order);

    void cancelOrder(String orderId);
}
