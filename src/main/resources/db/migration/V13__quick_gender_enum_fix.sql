alter table `patient`
    modify `gender` enum('male','female') NOT NULL COMMENT 'enum to represent the patients gender';

alter table `pending_patient`
    modify `gender` enum('male','female') NOT NULL COMMENT 'enum to represent the patients gender';