package com.example.projectsale.order.repo;

import com.example.projectsale.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Order, UUID> {
    // Tìm đơn hàng theo email hoặc tên khách hàng
    List<Order> findByEmailContainingOrFullNameContaining(String email, String fullName);

    // Tìm đơn hàng theo ID
    Optional<Order> findById(UUID orderId);

    // Tìm tất cả đơn hàng của một người dùng dựa trên userId
    List<Order> findByUserId(UUID userId);

    // Tìm đơn hàng theo trạng thái
    List<Order> findByStatus(String status);
}
