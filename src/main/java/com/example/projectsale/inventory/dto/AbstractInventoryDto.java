package com.example.projectsale.inventory.dto;

import com.example.projectsale.enums.StatusInventory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public abstract class AbstractInventoryDto {

    private String color;

    private UUID supplierId;

    @Positive
    private Integer quantityInStock;

    @Positive
    private Integer minimumInStock;

    @Positive
    private Integer maximumInStock;

    private Date lastRestockDate;

    private StatusInventory statusInventory;

    @NotBlank
    private String size;

}
