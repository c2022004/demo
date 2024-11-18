package com.example.projectsale.category.controller.admin;

import com.example.projectsale.category.constant.CategoryConstant;
import com.example.projectsale.category.dto.request.CategoryCreateRequest;
import com.example.projectsale.category.service.CategoryService;
import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.enums.SystemEnumStatus;
import com.example.projectsale.utils.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_ONE + CategoryConstant.API_CATEGORY)
public class CategoryController {

    private final CategoryService categoryService;

    @PreAuthorize("")
    @PostMapping
    public ResponseEntity<Response> createCategory(@RequestBody CategoryCreateRequest request) {
        return categoryService.createCategory(request);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> updateCategory(@PathVariable UUID id, CategoryCreateRequest request) {
        return categoryService.updateCategory(id, request);
    }

    @GetMapping
    public ResponseEntity<Response> getCategories(
            @RequestParam(SystemConstant.STATUS) Optional<SystemEnumStatus> status,
            @RequestParam(SystemConstant.CURRENT_PAGE) Optional<Integer> currentPage,
            @RequestParam(SystemConstant.LIMIT_PAGE) Optional<Integer> limitPage
    ) {
        return categoryService.getCategories(status.orElse(SystemEnumStatus.ACTIVE),
                currentPage.orElse(1),
                limitPage.orElse(8));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable UUID id) {
        return categoryService.deleteCategory(id);
    }
}
