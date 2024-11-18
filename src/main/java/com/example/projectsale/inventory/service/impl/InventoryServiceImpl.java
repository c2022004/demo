package com.example.projectsale.inventory.service.impl;

import com.example.projectsale.inventory.repo.InventoryRepo;
import com.example.projectsale.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepo inventoryRepo;

}
