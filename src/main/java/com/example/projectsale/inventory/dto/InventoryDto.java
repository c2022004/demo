package com.example.projectsale.inventory.dto;

import com.example.projectsale.category.dto.CategoryDto;
import com.example.projectsale.enums.StatusInventory;
import com.example.projectsale.product.dto.ProductDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Valid
public class InventoryDto {

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

    private UUID categoryId;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    private String shortDescription;

    private String longDescription;

    private MultipartFile[] images;
}
