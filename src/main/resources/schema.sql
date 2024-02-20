create table IF NOT EXISTS species(
    id uuid primary key default gen_random_uuid(),
    name varchar(100) not null
);

create table IF NOT EXISTS animal(
    id uuid primary key default gen_random_uuid(),
    name varchar(100) not null,
    species uuid,
    age int not null,
    arrival_date date,
    diet varchar(100),
    weight decimal not null,
    length decimal not null,
    habitat varchar(100),
    FOREIGN KEY (species) REFERENCES species(id)
);

create table IF NOT EXISTS users(
    id uuid primary key default gen_random_uuid(),
    login text not null unique,
    password text not null,
    role text not null
);