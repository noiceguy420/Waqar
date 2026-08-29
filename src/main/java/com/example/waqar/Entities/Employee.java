package com.example.waqar.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "employee", schema = "waqardb")
public class Employee {
    @Id
    @Size(max = 10)
    @Column(name = "id", nullable = false, length = 10)
    private String id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull
    @Lob
    @Column(name = "gender", nullable = false)
    private String gender;

    @Size(max = 9)
    @NotNull
    @Column(name = "phone_number_1", nullable = false, length = 9)
    private String phoneNumber1;

    @Size(max = 9)
    @Column(name = "phone_number_2", length = 9)
    private String phoneNumber2;

    @Size(max = 150)
    @NotNull
    @Column(name = "job_desc", nullable = false, length = 150)
    private String jobDesc;

    @Size(max = 150)
    @ColumnDefault("'not medical'")
    @Column(name = "spec", length = 150)
    private String spec;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "years_of_exp", nullable = false)
    private Byte yearsOfExp;

}