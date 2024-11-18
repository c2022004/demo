package com.example.projectsale.permission.repo;

import com.example.projectsale.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionServiceRepo extends JpaRepository<Permission, UUID> {
}
