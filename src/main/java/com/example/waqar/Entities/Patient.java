package com.example.waqar.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "patient", schema = "waqardb")
public class Patient {
    @Id
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
    @Lob
    @Column(name = "gender", nullable = false)
    private String gender;

    @Size(max = 15)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

    @NotNull
    @ColumnDefault("'none'")
    @Lob
    @Column(name = "chronic_diseases", nullable = false)
    private String chronicDiseases;

    @NotNull
    @Column(name = "client", nullable = false)
    private Integer client;

}