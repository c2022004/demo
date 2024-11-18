package com.example.projectsale.category.service;

import com.example.projectsale.category.dto.request.CategoryCreateRequest;
import com.example.projectsale.enums.SystemEnumStatus;
import com.example.projectsale.utils.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CategoryService {

    ResponseEntity<Response> createCategory(CategoryCreateRequest request);

    ResponseEntity<Response> updateCategory(UUID categoryId, CategoryCreateRequest request);

    ResponseEntity<Response> deleteCategory(UUID categoryId);

    ResponseEntity<Response> getCategories(SystemEnumStatus status, int currentPage, int limitPage);

}
