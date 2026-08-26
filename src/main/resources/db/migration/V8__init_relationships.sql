alter table patient
    add constraint patient_client_id_fk
        foreign key (client) references client (id);

alter table post_appointment_case_documentation
    add constraint post_appointment_case_documentation_appointment_id_fk
        foreign key (associated_appointment) references appointment (id);

alter table appointment
    add constraint appointment_patient_id_fk
        foreign key (patient_id) references patient (id);

