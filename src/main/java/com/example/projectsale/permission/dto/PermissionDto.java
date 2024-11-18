package com.example.projectsale.permission.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PermissionDto {

    private String permissionCode;

    private String status;

    private Boolean isDeleted;

    private String description;
}
