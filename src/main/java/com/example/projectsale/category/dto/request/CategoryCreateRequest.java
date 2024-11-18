package com.example.projectsale.category.dto.request;

import com.example.projectsale.enums.SystemEnumStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCreateRequest {

    private UUID id;

    private String name;

    private SystemEnumStatus status;

    private Boolean isDeleted;

    private MultipartFile image;

    private String description;

    public SystemEnumStatus getStatus() {
        return status == null ? SystemEnumStatus.ACTIVE : status;
    }

}
