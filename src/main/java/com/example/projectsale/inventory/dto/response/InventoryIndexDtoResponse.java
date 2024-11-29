package com.example.projectsale.inventory.dto.response;

import com.example.projectsale.enums.StatusInventory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
public class InventoryIndexDtoResponse {

    private UUID id;

    private Integer quantityInStock;

    private Integer minimumInStock;

    private Integer maximumInStock;

    private Date lastRestockDate;

    private StatusInventory statusInventory;

    private String size;

    private String color;

}
