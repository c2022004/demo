package com.example.projectsale.userrole.repo;

import com.example.projectsale.userrole.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, UUID> {
}
