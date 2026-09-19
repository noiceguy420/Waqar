package com.example.waqar.Repositories;

import com.example.waqar.Entities.PendingPatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingPatientRepo extends JpaRepository<PendingPatient, Integer> {
}
