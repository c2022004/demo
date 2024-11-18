package com.example.projectsale.permissionaction.repo;

import com.example.projectsale.permissionaction.entity.PermissionAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionActionRepo extends JpaRepository<PermissionAction, UUID> {
}
