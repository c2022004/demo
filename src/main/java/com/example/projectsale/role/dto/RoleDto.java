package com.example.projectsale.role.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
public class RoleDto {

    private String roleName;

    private String roleCode;

    private String status;

    private Boolean isDeleted;

}
