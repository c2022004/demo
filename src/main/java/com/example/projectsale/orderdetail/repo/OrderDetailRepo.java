package com.example.projectsale.orderdetail.repo;

import com.example.projectsale.orderdetail.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, UUID> {
}
