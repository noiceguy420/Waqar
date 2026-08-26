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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "post_appointment_case_documentation", schema = "waqardb")
public class PostAppointmentCaseDocumentation {
    @Id
    @Size(max = 16)
    @ColumnDefault("(uuid_to_bin(uuid()))")
    @Column(name = "Doc_Id", nullable = false, length = 16)
    private String docId;

    @Size(max = 16)
    @NotNull
    @Column(name = "associated_appointment", nullable = false, length = 16)
    private String associatedAppointment;

    @Size(max = 500)
    @NotNull
    @Column(name = "complaint", nullable = false, length = 500)
    private String complaint;

    @NotNull
    @Column(name = "form_submission_time", nullable = false)
    private Instant formSubmissionTime;

    @NotNull
    @Column(name = "vital_signs", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> vitalSigns;

    @NotNull
    @Column(name = "input", nullable = false)
    private Float input;

    @NotNull
    @Column(name = "output", nullable = false)
    private Float output;

    @Size(max = 200)
    @NotNull
    @Column(name = "procedures", nullable = false, length = 200)
    private String procedures;

    @NotNull
    @Column(name = "Drugs", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> drugs;

    @Size(max = 400)
    @Column(name = "Doctor_Note", length = 400)
    private String doctorNote;

}