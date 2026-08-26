create table Post_Appointment_Case_Documentation
(
    Doc_Id                 binary(16) default (uuid_to_bin(uuid())) not null comment 'document id'
        primary key,
    associated_appointment binary(16)                             not null comment 'appointment for this case doc',
    complaint              varchar(500)                           not null comment 'complaint at the time of filing',
    form_submission_time   datetime                               not null comment 'form submission time',
    vital_signs            json                                   not null comment 'patient vital signs',
    input                  float4                                 not null comment 'liquid (water) input of patient',
    output                 float4                                 not null comment 'liquid output of the patient',
    procedures             varchar(200)                           not null comment 'procedures done since last submission',
    Drugs                  json                                   not null comment 'drugs taken since last submission',
    Doctor_Note            varchar(400)                           null     comment 'Doctor note if needed or provided'
);