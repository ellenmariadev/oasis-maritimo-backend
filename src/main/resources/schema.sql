create table IF NOT EXISTS animal(
    id serial primary key,
    name varchar(100) not null,
    species varchar(100) not null,
    age int
);

create table IF NOT EXISTS users(
    id serial primary key not null,
    login text not null unique,
    password text not null,
    role text not null
);