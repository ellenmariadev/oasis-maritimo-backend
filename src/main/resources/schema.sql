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
    image_url varchar(255),
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
    name varchar(100) not null unique,
    user_id uuid REFERENCES users(id)
);

create table IF NOT EXISTS annotation_tags(
    annotation_id uuid,
    tag_id uuid,
    PRIMARY KEY (annotation_id, tag_id),
    FOREIGN KEY (annotation_id) REFERENCES annotation(id),
    FOREIGN KEY (tag_id) REFERENCES tag(id)
);

create table IF NOT EXISTS appointment(
    id uuid primary key default gen_random_uuid(),
    description text,
    date date,
    time time,
    veterinarian_id uuid,
    animal_id uuid,
    status varchar(20) check (status in ('SCHEDULED', 'COMPLETED', 'CANCELLED')) default 'SCHEDULED',
    FOREIGN KEY (veterinarian_id) REFERENCES users(id),
    FOREIGN KEY (animal_id) REFERENCES animal(id)
);
