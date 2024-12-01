package com.example.projectsale.inventory.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InventorySimpleDto {
    private String color;
    private String size;
}
