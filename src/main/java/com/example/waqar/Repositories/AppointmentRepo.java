package com.example.waqar.Repositories;

import com.example.waqar.Entities.Appointment;
import com.example.waqar.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface AppointmentRepo extends JpaRepository<Appointment, UUID> {

    @Query("SELECT Appointment a where a.patient = :patient AND a.dateTime >= System.now") //TODO: verify this jquery
    Set<Appointment> findFuturePatientAppointments(@Param("patient") Patient patient);
}
