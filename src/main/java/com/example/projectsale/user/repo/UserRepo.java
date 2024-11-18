package com.example.projectsale.user.repo;

import com.example.projectsale.enums.SystemEnumStatus;
import com.example.projectsale.user.entity.User;
import com.example.projectsale.utils.AbstractRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends AbstractRepository<User, UUID> {

    boolean existsUserByEmail(String email);

    @EntityGraph(attributePaths = {"userRoles", "userRoles.role", "userRoles.role.permissionRoles"})
    Optional<User> findUserByEmailAndStatus(String email, SystemEnumStatus status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE
                User u
            SET
                u.status = 'ACTIVE'
            WHERE
                u.email = ?1
            """)
    void activeUserByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
