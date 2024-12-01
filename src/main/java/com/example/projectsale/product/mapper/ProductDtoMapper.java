package com.example.projectsale.product.mapper;

import com.example.projectsale.category.dto.CategoryDto;
import com.example.projectsale.category.entity.Category;
import com.example.projectsale.imageproduct.dto.ImageProductDto;
import com.example.projectsale.imageproduct.entity.ImageProduct;
import com.example.projectsale.inventory.dto.InventoryDto;
import com.example.projectsale.inventory.entity.Inventory;
import com.example.projectsale.inventory.mapper.InventoryDtoMapper;
import com.example.projectsale.product.dto.ProductDto;
import com.example.projectsale.product.dto.response.ProductDetailDtoResponse;
import com.example.projectsale.product.dto.response.ProductIndexDtoResponse;
import com.example.projectsale.product.entity.Product;
import com.example.projectsale.supplier.entity.Supplier;
import com.example.projectsale.supplier.mapper.SupplierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ProductDtoMapper implements Function<Product, ProductDto> {

    private final InventoryDtoMapper inventoryDtoMapper;

    private final SupplierMapper supplierMapper;

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

    public ProductDetailDtoResponse toProductDetailResponse(Product product) {
        ProductIndexDtoResponse productIndexDtoResponse = toProductIndexDtoResponse(product);

        List<Inventory> inventories = product.getInventories();
        Supplier supplier = inventories.get(0).getSupplier();
        List<InventoryDto> inventoryDtos = inventories.stream()
                .map(inventoryDtoMapper)
                .toList();

        return ProductDetailDtoResponse.builder()
                .products(productIndexDtoResponse)
                .inventories(inventoryDtos)
                .supplier(supplierMapper.toDTO(supplier))
                .build();
    }

}
