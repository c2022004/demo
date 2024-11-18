package com.example.projectsale.product.mapper;

import com.example.projectsale.product.dto.ProductDto;
import com.example.projectsale.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProductDtoMapper implements Function<Product, ProductDto> {

    @Override
    public ProductDto apply(Product product) {
        return null;
    }
}
