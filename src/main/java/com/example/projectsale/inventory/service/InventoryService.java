package com.example.projectsale.inventory.service;

import com.example.projectsale.inventory.dto.InventoryDto;
import com.example.projectsale.inventory.dto.request.InventorySearchDtoRequest;
import com.example.projectsale.utils.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface InventoryService {

    ResponseEntity<Response> createInventory(InventoryDto inventoryDto);

    ResponseEntity<Response> updateInventory(InventoryDto inventoryDto, UUID id);

    void deleteInventory(UUID id);

    ResponseEntity<Response> getAllInventory(InventorySearchDtoRequest request);
}
