-- waqardb appointment-employee-relationship definition
create table `appointment_employee` (
                                        `id` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())) COMMENT 'relationship id',
                                        `employee_id` varchar(10) NOT NULL,
                                        `appointment_id` binary(16) NOT NULL,
                                        PRIMARY KEY (`id`),
                                        CONSTRAINT `employee_frk` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
                                        CONSTRAINT `appointment_frk` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`),
                                        UNIQUE KEY (`employee_id`, `appointment_id`)
);
