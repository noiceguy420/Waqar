package com.example.waqar.Repositories;

import com.example.waqar.Entities.Client;
import com.example.waqar.Entities.ClientSession;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SessionRepo extends JpaRepository<ClientSession, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ClientSession> findByTokenHash(byte[] hashedToken);

    @Modifying
    @Query("UPDATE ClientSession s SET s.active = NULL WHERE s.client = :client AND s.active = TRUE")
    @Transactional
    void revokeActiveByClient(@Param("client") Client client);

    @Modifying
    @Query("DELETE FROM ClientSession s WHERE s.client = :client")
    @Transactional
    void revokeAllClientSessions(@Param("client") Client client);
}
