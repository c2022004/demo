package com.example.projectsale.permissionrole.repo;

import com.example.projectsale.permissionrole.entity.PermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionRoleRepo extends JpaRepository<PermissionRole, UUID> {
}
