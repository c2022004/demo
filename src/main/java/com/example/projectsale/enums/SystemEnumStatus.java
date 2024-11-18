package com.example.projectsale.enums;

import lombok.Getter;

@Getter
public enum SystemEnumStatus {

    NO_ACTIVE(0, "NO_ACTIVE"),
    ACTIVE(1, "ACTIVE");

    private final int code;
    private final String name;

    // Enum constructor to initialize the fields
    SystemEnumStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getStatus(int code) {
        return switch (code) {
            case 0 -> NO_ACTIVE.name;
            case 1 -> ACTIVE.name;
            default -> throw new IllegalArgumentException("Invalid code: " + code);
        };
    }
}
