package com.example.projectsale.inventory.service.impl;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.enums.StatusInventory;
import com.example.projectsale.enums.SystemEnumStatus;
import com.example.projectsale.inventory.dto.InventoryDto;
import com.example.projectsale.inventory.dto.request.InventorySearchDtoRequest;
import com.example.projectsale.inventory.dto.response.InventoryIndexDtoResponse;
import com.example.projectsale.inventory.entity.Inventory;
import com.example.projectsale.inventory.mapper.InventoryDtoMapper;
import com.example.projectsale.inventory.repo.InventoryRepo;
import com.example.projectsale.inventory.service.InventoryService;
import com.example.projectsale.product.dto.ProductDto;
import com.example.projectsale.product.entity.Product;
import com.example.projectsale.product.repo.ProductRepo;
import com.example.projectsale.supplier.entity.Supplier;
import com.example.projectsale.supplier.repo.SupplierRepo;
import com.example.projectsale.utils.AbsServiceUtil;
import com.example.projectsale.utils.PageableUtil;
import com.example.projectsale.utils.response.Response;
import com.example.projectsale.utils.response.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl extends AbsServiceUtil implements InventoryService {

    private final InventoryRepo inventoryRepo;
    private final ProductRepo productRepo;
    private final ResponseUtil responseUtil;
    private final SupplierRepo supplierRepo;
    private final InventoryDtoMapper inventoryDtoMapper;


    @Override
    public ResponseEntity<Response> createInventory(InventoryDto inventoryDto) {
        if (inventoryRepo.existsBySizeAndProduct_Id(inventoryDto.getSize(),
                inventoryDto.getProductId()))
        {
            throw new RuntimeException("Product exists");
        } else if (inventoryRepo.existsById(inventoryDto.getSupplierId())) {
            throw new RuntimeException("Supplier exists");
        }

        Product product = productRepo.findById(inventoryDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Supplier supplier = supplierRepo.findById(inventoryDto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Inventory inventory = inventoryRepo.save(
                Inventory.builder()
                        .product(product)
                        .size(inventoryDto.getSize())
                        .quantityInStock(inventoryDto.getQuantityInStock())
                        .maximumInStock(inventoryDto.getMaximumInStock())
                        .minimumInStock(inventoryDto.getMinimumInStock())
                        .statusInventory(inventoryDto.getStatusInventory())
                        .lastRestockDate(new Date())
                        .supplier(supplier)
                        .status(SystemEnumStatus.ACTIVE)
                        .isDeleted(SystemConstant.IS_DELETED_ACTIVE)
                        .statusInventory(StatusInventory.NEW)
                        .build()
        );

        return responseUtil.responseSuccess("IV_001",null);
    }

    @Override
    public ResponseEntity<Response> updateInventory(InventoryDto inventoryDto,
                                                    UUID id) {
        if (!inventoryRepo.existsBySizeAndProduct_Id(inventoryDto.getSize(),
                inventoryDto.getProductId()))
        {
            throw new RuntimeException("Product not exists");
        }

        Supplier supplier = supplierRepo.findById(inventoryDto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Inventory inventory = inventoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        inventory.setQuantityInStock(inventoryDto.getQuantityInStock());
        inventory.setMaximumInStock(inventoryDto.getMaximumInStock());
        inventory.setMinimumInStock(inventoryDto.getMinimumInStock());
        inventory.setStatusInventory(inventoryDto.getStatusInventory());
        inventory.setLastRestockDate(new Date(System.currentTimeMillis()));
        inventory.setSize(inventoryDto.getSize());
        inventory.setSupplier(supplier);


        return null;
    }

    @Override
    public void deleteInventory(UUID id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setIsDeleted(SystemConstant.IS_DELETED_NO_ACTIVE);
        product.setStatus(SystemEnumStatus.NO_ACTIVE);

        Inventory inventory = inventoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setStatus(SystemEnumStatus.NO_ACTIVE);
        inventory.setStatusInventory(StatusInventory.DISCONTINUED);
        inventory.setIsDeleted(SystemConstant.IS_DELETED_NO_ACTIVE);
        productRepo.save(product);
    }


    @Override
    public ResponseEntity<Response> getAllInventory(InventorySearchDtoRequest request) {
        Pageable pageable = PageableUtil.of(request.getCurrentPage(), request.getLimitPage());
        Page<Inventory> all = inventoryRepo.findAllBy(request, pageable);
        List<InventoryIndexDtoResponse> list = all.stream()
                .map(inventoryDtoMapper::toInventoryIndexDtoResponse)
                .toList();
        return responseUtil.responseSuccess("List Inventory", list);
    }

}
