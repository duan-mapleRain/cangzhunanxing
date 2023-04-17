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
VALUES ('root', '超级管理员', '$2a$10$WgngmRj.zP4Y0hOfY/9Qmu60c2Nopg0AuRJp6Klp3i4oNBxkS7Flm', 2);

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
