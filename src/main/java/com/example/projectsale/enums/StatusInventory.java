package com.example.projectsale.enums;

import lombok.Getter;

@Getter
public enum StatusInventory {

    IN_STOCK,
    OUT_STOCK,
    LOW_STOCK,
    BACK_ORDERED,
    RESTOCK,
    RESERVED,
    DAMAGED,
    PENDING_INSPECTION,
    DISCONTINUED,
    PRE_ORDER

}
