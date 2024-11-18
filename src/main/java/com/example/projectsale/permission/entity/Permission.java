package com.example.projectsale.permission.entity;

import com.example.projectsale.permissionrole.entity.PermissionRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`permission`")
public class Permission {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "permisison_code", nullable = false)
    private String permissionCode;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "create_date")
    private Date createdDate;

    @Column(name = "create_by")
    private String createdBy;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @OneToMany(mappedBy = "permission", fetch = FetchType.LAZY)
    private Set<PermissionRole> permissionRoles;

}
