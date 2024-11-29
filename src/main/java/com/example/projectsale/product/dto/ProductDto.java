package com.example.projectsale.product.dto;

import com.example.projectsale.category.dto.CategoryDto;
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
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class ProductDto {

    private UUID id;

    private UUID categoryId;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    private String shortDescription;

    private String longDescription;

    private CategoryDto category;

    private MultipartFile[] images; // Allows multiple file uploads
}

