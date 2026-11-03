-- fixes for patient id and primary key
ALTER TABLE `appointment`
    MODIFY  COLUMN `id` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())) COMMENT 'appointment id',
    DROP CONSTRAINT `appointment_pk`,
    ADD UNIQUE KEY `patient_date_unique_key` (`patient_id`, `dateTime`);