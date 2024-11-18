package com.example.projectsale.user.entity;

import com.example.projectsale.order.entity.Order;
import com.example.projectsale.userprofile.entity.UserProfile;
import com.example.projectsale.userrole.entity.UserRole;
import com.example.projectsale.utils.base.BaseEntityUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`user`")
public class User extends BaseEntityUtil implements UserDetails {

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Order> orders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserRole> userRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getUserRoles().stream()
                .flatMap(userRole -> {
                    Set<SimpleGrantedAuthority> grantedAuthorities = userRole.getRole().getPermissionRoles().stream()
                            .map(permissionRole -> new SimpleGrantedAuthority(permissionRole.getPermissionCode().toUpperCase()))
                            .collect(Collectors.toSet());

                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRoleName().toUpperCase()));
                    return grantedAuthorities.stream();
                }).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
