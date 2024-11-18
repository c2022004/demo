package com.example.projectsale.confirmtoken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, UUID> {

    Optional<ConfirmationToken> findConfirmationTokenByToken(String token);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("""
                UPDATE ConfirmationToken c
                SET c.confirmedAt = ?2
                WHERE c.token = ?1
            """)
    void updateConfirmAtByToken(String token, LocalDateTime createdAt);

    Optional<List<ConfirmationToken>> findAllByConfirmedAtAndExpiresAtBefore(LocalDateTime confirmedAt, LocalDateTime expiresAt);
}
