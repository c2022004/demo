package com.example.projectsale.user.service;

import com.example.projectsale.permissionrole.entity.PermissionRole;
import com.example.projectsale.permissionrole.entity.PermissionRole_;
import com.example.projectsale.role.entity.Role;
import com.example.projectsale.role.entity.Role_;
import com.example.projectsale.user.entity.User;
import com.example.projectsale.user.entity.User_;
import com.example.projectsale.userprofile.entity.UserProfile;
import com.example.projectsale.userprofile.entity.UserProfile_;
import com.example.projectsale.userrole.entity.UserRole;
import com.example.projectsale.userrole.entity.UserRole_;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpec {

    public static Specification<User> findUsersByCondition(String key, String role) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> conditions = new ArrayList<>();
            Join<User, UserProfile> profileJoin = root.join(User_.userProfile, JoinType.LEFT);

            if (StringUtils.isNotBlank(key)) {
                String condition = String.format("%%%s%%", key.toLowerCase());

                Predicate f = criteriaBuilder.like(criteriaBuilder.lower(root.get(User_.EMAIL)), condition);
                Predicate e = criteriaBuilder.like(criteriaBuilder.lower(profileJoin.get(UserProfile_.PHONE_NUMBER)), condition);
                conditions.add(criteriaBuilder.or(f, e));
            }

            if (StringUtils.isNotBlank(role)) {
                Join<User, UserRole> userRoleJoin = root.join(User_.userRoles, JoinType.LEFT);
                Join<UserRole, Role> roleJoin = userRoleJoin.join(UserRole_.role, JoinType.LEFT);
                Join<Role, PermissionRole> permissionRoleJoin = roleJoin.join(Role_.permissionRoles, JoinType.LEFT);

                Predicate r = criteriaBuilder.equal(criteriaBuilder.lower(permissionRoleJoin.get(PermissionRole_.PERMISSION_CODE)), role.toLowerCase());
                conditions.add(r);
            }

            return criteriaBuilder.and(conditions.toArray(new Predicate[0]));
        };
    }
}
