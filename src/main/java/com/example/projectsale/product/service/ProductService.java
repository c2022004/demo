package com.example.projectsale.product.service;

import com.example.projectsale.product.dto.ProductDto;
import com.example.projectsale.product.dto.request.ProductSearchDtoRequest;
import com.example.projectsale.product.entity.Product;
import com.example.projectsale.utils.response.Response;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ProductService {

    ResponseEntity<Response> saveProduct(@RequestBody ProductDto productDto);

    ResponseEntity<Response> updateProduct(ProductDto productDto, UUID id);

    void deleteProduct(UUID id);

    ResponseEntity<Response> getProduct(UUID id);

    ResponseEntity<Response> findAllProducts(ProductSearchDtoRequest request);
}
