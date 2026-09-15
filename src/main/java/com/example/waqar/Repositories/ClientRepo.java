package com.example.waqar.Repositories;

import com.example.waqar.Entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Integer> {
    boolean existsByEmail(@NotBlank @Size(max = 70) String email);

    Optional<Client> findByUsername(String username);
}
