package com.example.projectsale.inventory.mapper;

import com.example.projectsale.enums.StatusInventory;
import com.example.projectsale.inventory.dto.InventoryDto;
import com.example.projectsale.inventory.dto.response.InventoryIndexDtoResponse;
import com.example.projectsale.inventory.entity.Inventory;
import com.example.projectsale.product.dto.ProductDto;
import com.example.projectsale.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class InventoryDtoMapper implements Function<Inventory, InventoryDto> {
    @Override
    public InventoryDto apply(Inventory inventory) {
        Product product = inventory.getProduct();
        return InventoryDto.builder()
                .size(inventory.getSize())
                .lastRestockDate(new Date())
                .quantityInStock(inventory.getQuantityInStock())
                .minimumInStock(inventory.getMinimumInStock())
                .maximumInStock(inventory.getMaximumInStock())
                .product(ProductDto.builder().id(product.getId()).name(product.getName()).build())
                .build();
    }

    public InventoryIndexDtoResponse toInventoryIndexDtoResponse(Inventory inventory) {
        return InventoryIndexDtoResponse.builder()
                .id(inventory.getId())
                .quantityInStock(inventory.getQuantityInStock())
                .maximumInStock(inventory.getMaximumInStock())
                .minimumInStock(inventory.getMinimumInStock())
                .statusInventory(StatusInventory.NEW)
                .lastRestockDate(inventory.getLastRestockDate())
                .build();
    }
}
