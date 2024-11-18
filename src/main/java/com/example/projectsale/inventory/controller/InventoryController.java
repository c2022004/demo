package com.example.projectsale.inventory.controller;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.inventory.constant.InventoryConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_ONE + InventoryConstant.URI_INVENTORY)
public class InventoryController {
}
