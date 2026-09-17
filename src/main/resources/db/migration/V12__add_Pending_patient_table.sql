CREATE TABLE `pending_patient` (
        `id` int NOT NULL AUTO_INCREMENT,
        `Name` varchar(100) NOT NULL COMMENT 'what would he like to be called',
        `date_of_birth` date NOT NULL COMMENT 'get age from here',
        `gender` enum('male','female') NOT NULL COMMENT '0 = female, 1 = male',
        `phone_number` varchar(15) NOT NULL COMMENT 'should contain phone number of the patient not client but wont be enforced',
        `client` int NOT NULL COMMENT 'client associated with patient',
        PRIMARY KEY (`id`),
        KEY `pending_patient_client_id_fk` (`client`),
        CONSTRAINT `pending_patient_client_id_fk` FOREIGN KEY (`client`) REFERENCES `client` (`id`)
);