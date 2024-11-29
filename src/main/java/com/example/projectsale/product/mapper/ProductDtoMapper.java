package com.example.projectsale.product.mapper;

import com.example.projectsale.category.dto.CategoryDto;
import com.example.projectsale.category.entity.Category;
import com.example.projectsale.imageproduct.dto.ImageProductDto;
import com.example.projectsale.imageproduct.entity.ImageProduct;
import com.example.projectsale.product.dto.ProductDto;
import com.example.projectsale.product.dto.response.ProductIndexDtoResponse;
import com.example.projectsale.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProductDtoMapper implements Function<Product, ProductDto> {

    @Override
    public ProductDto apply(Product product) {
        Category category = product.getCategory();

        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .longDescription(product.getLongDescription())
                .shortDescription(product.getShortDescription())
                .category(CategoryDto.builder().id(category.getId()).name(category.getName()).build())
                .build();
    }


    public ProductIndexDtoResponse toProductIndexDtoResponse(Product product) {
        List<ImageProduct> imageProducts = product.getImageProducts();
        List<ImageProductDto> imageProductDtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(imageProducts)) {
            imageProducts.forEach(imageProduct -> {
                ImageProductDto imageProductDto = ImageProductDto.builder().urlImage(imageProduct.getUrlImage()).build();
                imageProductDtos.add(imageProductDto);
            });
        }

        return ProductIndexDtoResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .shortDescription(product.getShortDescription())
                .longDescription(product.getLongDescription())
                .images(imageProductDtos)
                .build();
    }


}
