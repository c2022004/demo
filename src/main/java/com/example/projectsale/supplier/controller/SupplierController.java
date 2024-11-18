package com.example.projectsale.supplier.controller;

import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.supplier.constant.SupplierConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_ONE + SupplierConstant.SUPPLIER_URI)
public class SupplierController {



}
