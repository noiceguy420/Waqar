package com.example.waqar.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pending_patient", schema = "waqardb")
public class PendingPatient implements CustomWaqarEntities{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Integer id;

        @Size(max = 100)
        @NotNull
        @Column(name = "Name", nullable = false, length = 100)
        private String name;

        @NotNull
        @Column(name = "date_of_birth", nullable = false)
        private LocalDate dateOfBirth;

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(name = "gender", nullable = false)
        private Gender gender;

        @Size(max = 15)
        @NotNull
        @Column(name = "phone_number", nullable = false, length = 15)
        private String phoneNumber;

        @NotNull
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "client", nullable = false)
        private Client client;
}
