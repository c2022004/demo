package com.example.projectsale.order.service;

import com.example.projectsale.order.dto.OrderDto;
import com.example.projectsale.order.entity.Order;
import com.example.projectsale.order.event.OrderDischargeEvent;
import com.example.projectsale.order.mapper.OrderDtoMapper;
import com.example.projectsale.order.repo.OrderRepo;
import com.example.projectsale.utils.AbsServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl extends AbsServiceUtil implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderDtoMapper orderDtoMapper;
    private final ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void demo(String search) {
        log.info("========================== Demo =====================");
        applicationEventPublisher.publishEvent(new OrderDischargeEvent(this, search));
    }


    @Override
    public List<OrderDto> searchOrders(String search) {
        log.info("Searching orders with keyword: {}", search);
        List<Order> orders = orderRepo.findByEmailContainingOrFullNameContaining(search, search);

        return orders.stream().map(orderDtoMapper).toList();
    }


    @Override
    public OrderDto createOrder(Order order) {
        log.info("Creating new order for user: {}", order.getUser().getEmail());

        Order savedOrder = orderRepo.save(order);


        applicationEventPublisher.publishEvent(new OrderDischargeEvent(this, savedOrder.getId().toString()));


        return orderDtoMapper.apply(savedOrder);
    }


    @Override
    public OrderDto updateOrder(String orderId, Order order) {
        Optional<Order> existingOrderOpt = orderRepo.findById(UUID.fromString(orderId));
        if (existingOrderOpt.isEmpty()) {
            throw new RuntimeException("Order not found with id: " + orderId);
        }

        Order existingOrder = existingOrderOpt.get();

        existingOrder.setFullName(order.getFullName());
        existingOrder.setEmail(order.getEmail());
        existingOrder.setPhoneNumber(order.getPhoneNumber());
        existingOrder.setAddress(order.getAddress());
        existingOrder.setTotalPrice(order.getTotalPrice());
        existingOrder.setDescription(order.getDescription());


        Order updatedOrder = orderRepo.save(existingOrder);


        return orderDtoMapper.apply(updatedOrder);
    }

    @Override
    public void cancelOrder(String orderId) {

    }


//    @Override
//    public void cancelOrder(UUID orderId) {
//        Optional<Order> existingOrderOpt = orderRepo.findById(orderId);
//        if (existingOrderOpt.isEmpty()) {
//            throw new RuntimeException("Order not found with id: " + orderId);
//        }
//
//        Order existingOrder = existingOrderOpt.get();
//        existingOrder.setStatus("CANCELED");
//        orderRepo.save(existingOrder);
//
//        log.info("Order {} has been canceled", orderId);
//    }
}
