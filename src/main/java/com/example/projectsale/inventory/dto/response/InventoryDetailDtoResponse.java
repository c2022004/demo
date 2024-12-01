package com.example.projectsale.inventory.dto.response;

import com.example.projectsale.enums.StatusInventory;
import com.example.projectsale.product.dto.ProductDto;
import com.example.projectsale.supplier.dto.SupplierDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class InventoryDetailDtoResponse {

    private SupplierDto supplier;

    private ProductDto product;

    private List<InventoryDtoResponse> inventories;

    @Getter
    @Setter
    @Builder
    public static class InventoryDtoResponse {

        private String color;

        private UUID supplierId;

        private Integer quantityInStock;

        private Integer minimumInStock;

        private Integer maximumInStock;

        private Date lastRestockDate;

        private StatusInventory statusInventory;

        private String size;

    }

}
