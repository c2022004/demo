package com.example.projectsale.product.repo;

import com.example.projectsale.product.dto.request.ProductSearchDtoRequest;
import com.example.projectsale.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {

    boolean existsByName(String name);



    @Query("""
            select
                p
            FROM
                Product p
            INNER JOIN
                Inventory i ON i.product.id = p.id
            INNER JOIN
                Supplier s ON s.id = i.supplier.id
            INNER JOIN
                Category c ON c.id = p.category.id
            WHERE
                p.status = 'ACTIVE'
                AND i.quantityInStock > 0
                AND i.status = 'ACTIVE'
                AND s.status = 'ACTIVE'
                AND c.status = 'ACTIVE'
                OR ((:#{#request.minPrice} IS NULL OR p.price BETWEEN :#{#request.minPrice}
                AND :#{#request.maxPrice})
                AND (:#{#request.name} IS NULL OR p.name = :#{#request.name})
                AND (:#{#request.categories} IS NULL OR p.category = :#{#request.categories})
                AND (:#{#request.size} IS NULL OR i.size = :#{#request.size})
                AND (:#{#request.color} IS NULL OR i.color = :#{#request.color})
            )
            """)
    Page<Product> findAllBy(@Param("request") ProductSearchDtoRequest request, Pageable pageable);
}
