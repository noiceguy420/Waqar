package com.example.waqar.Repositories;

import com.example.waqar.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentRepo extends JpaRepository<Appointment, UUID> {
}
