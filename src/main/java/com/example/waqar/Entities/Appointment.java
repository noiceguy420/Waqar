package com.example.waqar.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "appointment", schema = "waqardb")
public class Appointment implements CustomWaqarEntities{
    @Id
    @Size(max = 16)
    @Column(name = "id", nullable = false, length = 16)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @NotNull
    @Column(name = "dateTime", nullable = false)
    private LocalDateTime dateTime;

    @Size(max = 300)
    @NotNull
    @Column(name = "complaint", nullable = false, length = 300)
    private String complaint;

    @NotNull
    @Column(name = "needs_a_doctor", nullable = false)
    private Boolean needsADoctor = false;

    @NotNull
    @Column(name = "went_to_a_doctor", nullable = false)
    private Boolean wentToADoctor = false;

    @NotNull
    @Column(name = "status" , nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @OneToMany(mappedBy = "associatedAppointment")
    private Set<PostAppointmentCaseDocumentation> postAppointmentCaseDocumentations = new HashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location", nullable = false)
    private Location location;

    @ManyToMany()
    @JoinTable(
            name = "appointment_employee",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees = new HashSet<>();

}