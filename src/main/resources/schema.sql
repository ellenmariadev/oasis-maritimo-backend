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

create table IF NOT EXISTS task(
    id uuid primary key default gen_random_uuid(),
    title varchar(100) not null,
    description text,
    status varchar(20) check (status in ('TODO', 'DOING', 'COMPLETED')) default 'TODO',
    created_at timestamp DEFAULT current_timestamp,
    author_id uuid,
    animal_id uuid,
    FOREIGN KEY (author_id) REFERENCES users(id),
    FOREIGN KEY (animal_id) REFERENCES animal(id)
);

-- DROP TABLE IF EXISTS annotation;
-- DROP TABLE IF EXISTS tag;
-- DROP TABLE IF EXISTS annotation_tags;

create table IF NOT EXISTS annotation(
    id uuid primary key default gen_random_uuid(),
    title varchar(100) not null,
    description text,
    created_at timestamp DEFAULT current_timestamp,
    author_id uuid,
    animal_id uuid not null,
    FOREIGN KEY (author_id) REFERENCES users(id),
    FOREIGN KEY (animal_id) REFERENCES animal(id)
);

create table IF NOT EXISTS tag(
    id uuid primary key default gen_random_uuid(),
    name varchar(100) not null unique
);

create table IF NOT EXISTS annotation_tags(
    annotation_id uuid,
    tag_id uuid,
    PRIMARY KEY (annotation_id, tag_id),
    FOREIGN KEY (annotation_id) REFERENCES annotation(id),
    FOREIGN KEY (tag_id) REFERENCES tag(id)
);