create database if not exists cangzhu;
-- create database
use cangzhu;

create table if not exists p_user
(
    username char(64)      not null,
    account  char(64),
    password char(128)     not null,
    status   int default 0 not null,
    id       int auto_increment,
    primary key (id)
);

insert into p_user (username, account, password, status)
VALUES ('root', '超级管理员', '$2a$10$LORDv0Kr2mF0fb6Vc7J1VeZdGdAIK2mQlG/Bu67PurGij8cOg04cq', 2);

create table if not exists p_device_type
(
    update_time   timestamp,
    register_time timestamp,
    info          TEXT(128),
    name          TEXT(64) not null,
    id            int auto_increment,
    primary key (id)
);

create table if not exists p_device
(
    id             int auto_increment,
    type           int,
    name           TEXT(64),
    address        TEXT(64),
    uuid           char(128) not null,
    need_bind_person boolean,
    personId       int,
    register_time  timestamp,
    last_time      timestamp,
    primary key (id)
);
