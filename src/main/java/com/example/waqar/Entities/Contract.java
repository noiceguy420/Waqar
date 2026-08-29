package com.example.waqar.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "contract", schema = "waqardb")
public class Contract {
    @Id
    @Size(max = 16)
    @Column(name = "id", nullable = false, length = 16)
    private UUID id;

    //TODO [Reverse Engineering] generate columns from DB
}