package com.example.projectsale.inventory.service.impl;

import com.example.projectsale.inventory.repo.InventoryRepo;
import com.example.projectsale.inventory.service.InventoryService;
import com.example.projectsale.utils.AbsServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl extends AbsServiceUtil implements InventoryService {

    private final InventoryRepo inventoryRepo;



}
