package com.example.projectsale.inventory.repo;

import com.example.projectsale.inventory.dto.request.InventorySearchDtoRequest;
import com.example.projectsale.inventory.entity.Inventory;
import com.example.projectsale.product.dto.request.ProductSearchDtoRequest;
import com.example.projectsale.product.entity.Product;
import com.example.projectsale.utils.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepo extends AbstractRepository<Inventory, UUID> {
    boolean existsById(UUID id);

    boolean existsBySizeAndColorAndProductName(String size,
                                                      String productColor,
                                                      String productName);

    @Query("""
            select
                i
            FROM
                 Inventory i
            INNER JOIN
               Product p ON i.product.id = p.id
            INNER JOIN
                Supplier s ON s.id = i.supplier.id
            INNER JOIN
                Category c ON c.id = p.category.id
            WHERE
                (p.status = 'ACTIVE' OR p.status = 'NO_ACTIVE')
                AND (i.quantityInStock > 0 OR i.quantityInStock = 0)
                AND (i.status = 'ACTIVE' OR i.status = 'NO_ACTIVE')
                AND (s.status = 'ACTIVE' OR s.status = 'NO_ACTIVE')
                AND (c.status = 'ACTIVE' OR c.status = 'NO_ACTIVE')
                OR ((:#{#request.minPrice} IS NULL OR p.price BETWEEN :#{#request.minPrice}
                AND :#{#request.maxPrice})
                AND (:#{#request.color} IS NULL OR i.color = :#{#request.color})
                AND (:#{#request.name} IS NULL OR p.name = :#{#request.name})
                AND (:#{#request.categories} IS NULL OR p.category = :#{#request.categories})
                AND (:#{#request.size} IS NULL OR i.size = :#{#request.size})
                AND (:#{#request.statusInventory} IS NULL
                    OR i.statusInventory = :#{#request.statusInventory})
            )
            """)
    Page<Inventory> findAllBy(@Param("request") InventorySearchDtoRequest request, Pageable pageable);

    @Query("""
            select
                  i
            FROM
                  Inventory i
            WHERE
                  i.status = 'ACTIVE'
                  AND
                  i.product.id = :productId
                  AND i.size = :size
                  AND i.color = :color
            """)
    Optional<Inventory> findByInventory(UUID productId, String size, String color);
}
