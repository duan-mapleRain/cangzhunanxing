create database if not exists cangzhu;
-- create database
use cangzhu;

create table if not exists p_user
(
    email  char(64)      not null,
    password char(128)     not null,
    status int default 0 not null,
    id     int auto_increment,
    primary key (id)
)