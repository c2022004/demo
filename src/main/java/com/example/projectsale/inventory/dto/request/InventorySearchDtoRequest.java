package com.example.projectsale.inventory.dto.request;

import com.example.projectsale.enums.StatusInventory;
import com.example.projectsale.utils.Paging;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class InventorySearchDtoRequest extends Paging {
    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private String name;

    private List<String> categories;

    private String color;

    private String size;

    private StatusInventory statusInventory;
}
