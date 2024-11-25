package com.example.projectsale.category.service;

import com.example.projectsale.category.dto.CategoryDto;
import com.example.projectsale.category.dto.request.CategoryCreateRequest;
import com.example.projectsale.category.entity.Category;
import com.example.projectsale.category.mapper.CategoryDtoMapper;
import com.example.projectsale.category.repo.CategoryRepo;
import com.example.projectsale.constant.SystemConstant;
import com.example.projectsale.enums.SystemEnumStatus;
import com.example.projectsale.exception.ApiRequestException;
import com.example.projectsale.image.ImageService;
import com.example.projectsale.utils.AbsServiceUtil;
import com.example.projectsale.utils.base.BaseUrlServiceUtil;
import com.example.projectsale.utils.response.Response;
import com.example.projectsale.utils.response.ResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl extends AbsServiceUtil implements CategoryService {

    private final CategoryRepo categoryRepo;

    private final ResponseUtil responseUtil;

    private final CategoryDtoMapper categoryDtoMapper;

    private final ImageService imageService;

    private final BaseUrlServiceUtil baseUrlServiceUtil;

    @Override
    public ResponseEntity<Response> createCategory(CategoryCreateRequest request) {
        try {
            String categoryName = request.getName().trim().toLowerCase();
            if (categoryRepo.existsCategoryByName(categoryName)) {
                return responseUtil.responseError("CTG_001");
            }

            Category category = categoryRepo.save(
                    Category.builder()
                            .name(categoryName)
                            .description(request.getDescription())
                            .status(SystemEnumStatus.ACTIVE)
                            .isDeleted(SystemConstant.IS_DELETED_ACTIVE)
                            .build()
            );

            return responseUtil.responseSuccess("CM_002", categoryDtoMapper.apply(category));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new ApiRequestException("CM_001");
        }
    }

    @Override
    public ResponseEntity<Response> updateCategory(UUID categoryId, CategoryCreateRequest request) {
        try {
            Category category = getCategory(categoryId);

            String categoryName = request.getName().trim().toLowerCase();
            if (categoryRepo.existsCategoryByName(categoryName)) {
                return responseUtil.responseError("CTG_001");
            }

            category.setName(categoryName);
            category.setStatus(request.getStatus());
            category.setIsDeleted(request.getIsDeleted());
            category.setDescription(request.getDescription());

            if (request.getImage() != null) {
                String urlImage = imageService.saveImage(request.getImage());
                category.setImage(urlImage);
            }

            categoryRepo.save(category);
            return responseUtil.responseSuccess("CM_003", categoryDtoMapper.apply(category));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiRequestException("CM_001");
        }
    }

    @Override
    public ResponseEntity<Response> deleteCategory(UUID categoryId) {
        try {
            Category category = getCategory(categoryId);

            category.setStatus(SystemEnumStatus.NO_ACTIVE);
            category.setIsDeleted(SystemConstant.IS_DELETED_NO_ACTIVE);
            Category categorySave = categoryRepo.save(category);

            return responseUtil.responseSuccess("CM_004", categoryDtoMapper.apply(categorySave));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiRequestException("CM_001", e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Response> getCategories(SystemEnumStatus status, int currentPage, int limitPage) {
        try {
            Pageable pageable = pageable(currentPage, limitPage);
            Page<Category> all = categoryRepo.findCategoriesByStatus(status, pageable);
            List<CategoryDto> categories = all.stream()
                    .map(categoryDtoMapper)
                    .toList();

            return responseUtil.responsesSuccess("CTG_003", categories, pageable(pageable, all.getTotalPages()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiRequestException("CM_001");
        }
    }

    private Category getCategory(UUID categoryId) {
        return categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ApiRequestException("CTG_002"));
    }
}
