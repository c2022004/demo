package com.example.projectsale.category.mapper;

import com.example.projectsale.category.dto.CategoryDto;
import com.example.projectsale.category.entity.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CategoryDtoMapper implements Function<Category, CategoryDto> {

    private final ModelMapper modelMapper;

    @Override
    public CategoryDto apply(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
