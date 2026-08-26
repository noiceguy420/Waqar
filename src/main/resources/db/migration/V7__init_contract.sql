create table contract(
    id      binary(16) default (uuid_to_bin(uuid())) not null comment 'contract id'
                     primary key
);