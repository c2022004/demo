package com.example.projectsale.product.dto.response;

import com.example.projectsale.imageproduct.dto.ImageProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ProductIndexDtoResponse {

    private UUID id;

    private String name;

    private BigDecimal price;

    private String shortDescription;

    private String longDescription;

    private List<ImageProductDto> images;
}
