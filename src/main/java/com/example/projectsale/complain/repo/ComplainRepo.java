package com.example.projectsale.complain.repo;

import com.example.projectsale.complain.entity.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComplainRepo extends JpaRepository<Complain, UUID> {
}
