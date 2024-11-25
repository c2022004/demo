package com.example.projectsale.inventory.repo;

import com.example.projectsale.inventory.entity.Inventory;
import com.example.projectsale.utils.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface InventoryRepo extends AbstractRepository<Inventory, UUID> {

}
