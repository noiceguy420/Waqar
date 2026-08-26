create table employee
(
    id             VARCHAR(10)       not null comment 'employees nat. id used as pk'
        primary key,
    name           varchar(100)       not null comment 'employee name as in nat. id',
    gender         enum ('male', 'female') not null comment '0 = female, 1 = male',
    phone_number_1 varchar(9)        not null comment 'first phone number mandatory',
    phone_number_2 varchar(9)        null comment 'just in case 2nd phone number',
    job_desc       varchar(150)      not null comment 'describes employees job',
    spec           varchar(150)      default 'not medical' comment 'employee (medical)specialty',
    years_of_exp   TINYINT default 0 not null comment 'years of exp of the employee'
    #comment 'add a file for official docoments named /employees/{id}/official_Docs'
    #comment 'add a file for certifications named /employees/{id}/certifications'
    #comment 'add a file for certifications named /employees/{id}/certifications'
);