package com.example.waqar.Repositories;

import com.example.waqar.Entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContractRepo extends JpaRepository<Contract, UUID> {
}
