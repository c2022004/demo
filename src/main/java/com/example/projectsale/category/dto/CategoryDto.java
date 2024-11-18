package com.example.projectsale.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CategoryDto {

    private UUID id;

    private String name;

    private String image;

    private String status;

    private Boolean isDeleted;

    private String description;
}

