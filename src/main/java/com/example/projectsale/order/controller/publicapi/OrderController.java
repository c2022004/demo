package com.example.projectsale.order.controller.publicapi;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.order.constant.OrderConstant;
import com.example.projectsale.order.dto.OrderDto;
import com.example.projectsale.order.entity.Order;
import com.example.projectsale.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_PUBLIC + SystemConstant.VERSION_ONE + OrderConstant.API_ORDER)
public class OrderController {

    private final OrderService orderService;

    // Endpoint tìm kiếm đơn hàng
    @GetMapping(OrderConstant.API_ORDER_SEARCH + "/{search}")
    public ResponseEntity<?> search(@PathVariable String search) {
        List<OrderDto> orders = orderService.searchOrders(search);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Endpoint tạo đơn hàng
    @PostMapping(OrderConstant.API_ORDER_CREATE)
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.CREATED);
    }

    // Endpoint cập nhật đơn hàng
    @PutMapping(OrderConstant.API_ORDER_UPDATE + "/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable String orderId, @RequestBody Order order) {
        return new ResponseEntity<>(orderService.updateOrder(orderId, order), HttpStatus.OK);
    }

    // Endpoint hủy đơn hàng
    @DeleteMapping(OrderConstant.API_ORDER_CANCEL + "/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable String orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
