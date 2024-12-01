package com.example.projectsale.order.service;

import com.example.projectsale.order.dto.OrderDto;
import com.example.projectsale.order.dto.request.OrderSearchDtoRequest;
import com.example.projectsale.order.entity.Order;
import com.example.projectsale.utils.response.Response;
import org.springframework.http.ResponseEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderService {

    ResponseEntity<Response> getAllOrders(OrderSearchDtoRequest request);



    OrderDto updateOrder(String orderId, Order order);

    void cancelOrder(String orderId);
}
