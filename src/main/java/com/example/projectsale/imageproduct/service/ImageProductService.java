package com.example.projectsale.imageproduct.service;

import com.example.projectsale.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ImageProductService {

    void createImageProduct(Product product, MultipartFile[] imageProduct);
}
