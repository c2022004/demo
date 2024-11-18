package com.example.projectsale.role.mapper;

import com.example.projectsale.role.dto.RoleDto;
import com.example.projectsale.role.entity.Role;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RoleDtoMapper implements Function<Role, RoleDto> {

    @Override
    public RoleDto apply(Role role) {
        return null;
    }
}
