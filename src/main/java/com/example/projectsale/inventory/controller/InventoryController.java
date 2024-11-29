package com.example.projectsale.inventory.controller;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.inventory.constant.InventoryConstant;
import com.example.projectsale.inventory.dto.InventoryDto;
import com.example.projectsale.inventory.dto.request.InventorySearchDtoRequest;
import com.example.projectsale.inventory.service.InventoryService;
import com.example.projectsale.utils.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_ONE + InventoryConstant.URI_INVENTORY)
public class InventoryController {
    private final InventoryService inventoryService;


    @GetMapping
    public ResponseEntity<Response> getAllInventory(InventorySearchDtoRequest request) {
        return inventoryService.getAllInventory(request);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> createInventory(InventoryDto inventoryDto) {
        inventoryService.createInventory(inventoryDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateInventory(@PathVariable UUID id, InventoryDto inventoryDto) {
        inventoryService.updateInventory(inventoryDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteInventory(@PathVariable UUID id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok().build();
    }
}
