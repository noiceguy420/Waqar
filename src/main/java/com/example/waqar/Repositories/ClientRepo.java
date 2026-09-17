package com.example.waqar.Repositories;

import com.example.waqar.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Integer> {
    boolean existsByEmail(String email);

    Optional<Client> findByUsername(String username);
    Optional<Client> findByEmail(String email);
}
