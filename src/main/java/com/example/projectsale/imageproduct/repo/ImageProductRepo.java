package com.example.projectsale.imageproduct.repo;

import com.example.projectsale.imageproduct.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageProductRepo extends JpaRepository<ImageProduct, UUID> {
}
