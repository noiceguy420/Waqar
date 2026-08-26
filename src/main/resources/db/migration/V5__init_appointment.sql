create table appointment
(
    id               binary(16)   not null default (uuid_to_bin(uuid())) comment 'appointment id mostly for case_doc'
        primary key,
    patient_id       int          not null comment 'patient id',
    dateTime         datetime     not null comment 'appointments date and time',
    complaint        varchar(300) not null comment 'what does the patient complain from',
    needs_a_doctor   bool         not null comment 'does this patient with this complaint need a doctor',
    went_to_a_doctor int          not null comment 'did the patient already see a doctor for this complaint',
    #comment 'what is p.o.R'
    constraint appointment_pk
        unique (patient_id, dateTime)
);