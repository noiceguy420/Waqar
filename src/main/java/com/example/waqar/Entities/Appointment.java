package com.example.waqar.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "appointment", schema = "waqardb")
public class Appointment {
    @Id
    @Size(max = 16)
    @ColumnDefault("(uuid_to_bin(uuid()))")
    @Column(name = "id", nullable = false, length = 16)
    private String id;

    @NotNull
    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @NotNull
    @Column(name = "dateTime", nullable = false)
    private Instant dateTime;

    @Size(max = 300)
    @NotNull
    @Column(name = "complaint", nullable = false, length = 300)
    private String complaint;

    @NotNull
    @Column(name = "needs_a_doctor", nullable = false)
    private Boolean needsADoctor = false;

    @NotNull
    @Column(name = "went_to_a_doctor", nullable = false)
    private Integer wentToADoctor;

}