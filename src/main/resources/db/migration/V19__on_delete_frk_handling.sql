ALTER TABLE `appointment_employee`
    DROP CONSTRAINT `appointment_frk`;
ALTER TABLE `appointment_employee`
    ADD CONSTRAINT `appointment_frk` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `appointment`
    DROP CONSTRAINT `appointment_patient_id_fk`;
ALTER TABLE `appointment`
    ADD CONSTRAINT `appointment_patient_id_fk` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `patient`
    DROP CONSTRAINT `patient_client_id_fk`;
ALTER TABLE `patient`
    ADD CONSTRAINT `patient_client_id_fk` FOREIGN KEY (`client`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `post_appointment_case_documentation`
    DROP CONSTRAINT `post_appointment_case_documentation_appointment_id_fk`,
    MODIFY COLUMN `associated_appointment` binary(16) NULL COMMENT 'appointment for this case doc';
ALTER TABLE `post_appointment_case_documentation`
    ADD CONSTRAINT `post_appointment_case_documentation_appointment_id_fk` FOREIGN KEY (`associated_appointment`) REFERENCES `appointment` (`id`) ON DELETE SET NULL On UPDATE CASCADE;