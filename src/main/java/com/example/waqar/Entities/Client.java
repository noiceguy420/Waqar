package com.example.waqar.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client", schema = "waqardb")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 70)
    @NotNull
    @Column(name = "email", nullable = false, length = 70)
    private String email;

    @Size(max = 30)
    @NotNull
    @Column(name = "Username", nullable = false, length = 30)
    private String username;

    @Size(max = 65)
    @NotNull
    @Column(name = "Pass_hash", nullable = false, length = 65)
    private String passHash;

    @Size(max = 15)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 15)
    private String phoneNumber;

}