package com.example.projectsale.product.controller.admin;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.product.constants.ProductConstant;
import com.example.projectsale.product.dto.ProductDto;
import com.example.projectsale.product.service.ProductService;
import com.example.projectsale.utils.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController(value = "adminProduct")
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_ONE + ProductConstant.PRODUCT_URI)
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> saveProduct(ProductDto request) {
        productService.saveProduct(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable("id") UUID id, @RequestBody ProductDto request) {
        productService.updateProduct(request,id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable("id") UUID id) {
       productService.deleteProduct(id);
       return ResponseEntity.ok().build();
    }


}
