package com.example.projectsale.order.repo;

import com.example.projectsale.order.dto.request.OrderSearchDtoRequest;
import com.example.projectsale.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Order, UUID> {
    @Query("""
                    select
                        o
                    FROM
                        Order o
                    INNER JOIN
                        OrderDetail d ON d.order.id = o.id
                    INNER JOIN
                        Product p ON p.id = d.product.id
                    WHERE
                        o.status = 'ACTIVE'
                        AND(:#{#request.email} IS NULL OR o.email = :#{#request.email})
                        AND(:#{#request.fullName} IS NULL OR o.fullName = :#{#request.fullName})
                        AND(:#{#request.phoneNumber} IS NULL OR o.phoneNumber = :#{#request.phoneNumber})
                        AND(:#{#request.address} IS NULL OR o.address = :#{#request.address})
                        AND(:#{#request.orderDate} IS NULL OR o.orderDate = :#{#request.orderDate})
                        AND(:#{#request.statusOrder} IS NULL OR o.statusOrder = :#{#request.statusOrder})
                        AND(:#{#request.productName} IS NULL OR p.name = :#{#request.productName})
            """)
    Page<Order> findAll(OrderSearchDtoRequest request, Pageable pageable);
}
