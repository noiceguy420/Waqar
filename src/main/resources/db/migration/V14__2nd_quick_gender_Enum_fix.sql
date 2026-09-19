alter table `patient`
    modify `gender` enum('MALE','FEMALE') NOT NULL COMMENT 'enum to represent the patients gender';

alter table `pending_patient`
    modify `gender` enum('MALE','FEMALE') NOT NULL COMMENT 'enum to represent the patients gender';