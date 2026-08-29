alter table appointment
    modify went_to_a_doctor bool not null comment 'did the patient already see a doctor for this complaint';