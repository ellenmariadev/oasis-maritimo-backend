create table IF NOT EXISTS animal(
    id serial primary key,
    name varchar(100) not null,
    species varchar(100) not null,
    age int
);
