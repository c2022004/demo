package com.example.projectsale.inventory.service;

import com.example.projectsale.inventory.dto.request.InventoryDtoRequest;
import com.example.projectsale.inventory.dto.request.InventorySearchDtoRequest;
import com.example.projectsale.utils.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface InventoryService {

    ResponseEntity<Response> createInventory(InventoryDtoRequest request);

    ResponseEntity<Response> updateInventory(InventoryDtoRequest request, UUID id);

    ResponseEntity<Response> deleteInventory(UUID id);

    ResponseEntity<Response> getAllInventory(InventorySearchDtoRequest request);
}
