package com.example.projectsale.inventory.dto.response;

import com.example.projectsale.inventory.dto.AbstractInventoryDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class InventoryIndexDtoResponse extends AbstractInventoryDto {

    private UUID id;

}
