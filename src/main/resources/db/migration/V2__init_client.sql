create table client
(
    id       int auto_increment
        primary key,
    email    VARCHAR(70) not null,
    Username varchar(30) not null,
    Pass_hash varchar(65) not null,
    phone_number varchar(15) not null comment 'the clients phone number',
    constraint client_pk_2
        unique (email)
);