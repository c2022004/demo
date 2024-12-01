package com.example.projectsale.product.dto.response;

import com.example.projectsale.inventory.dto.InventoryDto;
import com.example.projectsale.supplier.dto.SupplierDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductDetailDtoResponse {

    private SupplierDto supplier;

    private ProductIndexDtoResponse products;

    private List<InventoryDto> inventories;

}
