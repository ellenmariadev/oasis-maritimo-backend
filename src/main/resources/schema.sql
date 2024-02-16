create table IF NOT EXISTS Animals(
    id serial primary key,
    name varchar(100) not null,
    species varchar(100) not null,
    age int
);
