package com.example.projectsale.inventory.mapper;

import com.example.projectsale.enums.StatusInventory;
import com.example.projectsale.inventory.dto.InventoryDto;
import com.example.projectsale.inventory.dto.response.InventoryDetailDtoResponse;
import com.example.projectsale.inventory.dto.response.InventoryIndexDtoResponse;
import com.example.projectsale.inventory.entity.Inventory;
import com.example.projectsale.product.entity.Product;
import com.example.projectsale.product.mapper.ProductDtoMapper;
import com.example.projectsale.supplier.entity.Supplier;
import com.example.projectsale.supplier.mapper.SupplierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class InventoryDtoMapper implements Function<Inventory, InventoryDto> {

//    private final ProductDtoMapper productDtoMapper;
//
//    private final SupplierMapper supplierMapper;


    @Override
    public InventoryDto apply(Inventory inventory) {
        return InventoryDto.builder()
                .size(inventory.getSize())
                .lastRestockDate(new Date())
                .quantityInStock(inventory.getQuantityInStock())
                .minimumInStock(inventory.getMinimumInStock())
                .maximumInStock(inventory.getMaximumInStock())
                .color(inventory.getColor())
                .supplierId(inventory.getSupplier().getId())
                .statusInventory(inventory.getStatusInventory())
                .build();
    }

    public InventoryIndexDtoResponse toInventoryIndexDtoResponse(Inventory inventory) {
        return InventoryIndexDtoResponse.builder()
                .id(inventory.getId())
                .quantityInStock(inventory.getQuantityInStock())
                .maximumInStock(inventory.getMaximumInStock())
                .minimumInStock(inventory.getMinimumInStock())
                .color(inventory.getColor())
                .size(inventory.getSize())
                .statusInventory(StatusInventory.NEW)
                .lastRestockDate(inventory.getLastRestockDate())
                .build();
    }

//    public InventoryDetailDtoResponse inventoryDetailDtoResponse(Inventory inventory) {
//        Product product = inventory.getProduct();
//        Supplier supplier = inventory.getSupplier();
//
//        return InventoryDetailDtoResponse.builder()
//                .supplier(Objects.nonNull(supplier) ? supplierMapper.toDTO(supplier) : null)
//                .product(Objects.nonNull(product) ? productDtoMapper.apply(product) : null)
//                .build();
//    }
//
//    private InventoryDetailDtoResponse.InventoryDtoResponse toInventoryDtoResponse(Inventory inventory) {
//        return InventoryDetailDtoResponse.InventoryDtoResponse.builder()
//                .supplierId(inventory.getSupplier().getId())
//                .color(inventory.getColor())
//                .size(inventory.getSize())
//                .quantityInStock(inventory.getQuantityInStock())
//                .minimumInStock(inventory.getMinimumInStock())
//                .maximumInStock(inventory.getMaximumInStock())
//                .lastRestockDate(inventory.getLastRestockDate())
//                .statusInventory(inventory.getStatusInventory())
//                .build();
//    }
}
