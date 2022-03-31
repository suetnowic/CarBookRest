DROP TABLE IF EXISTS user_role CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS cars CASCADE;

CREATE TABLE users
(
    id       bigint       not null,
    username varchar(255) not null unique,
    email    varchar(255) not null unique,
    password varchar(120),
    primary key (id)
);

CREATE TABLE user_role
(
    user_id int8 not null,
    roles   varchar(50)
);

CREATE TABLE cars
(
    id                  bigint not null,
    car_brand           varchar(50),
    car_model           varchar(50),
    car_generation      varchar(50),
    year_of_issue       timestamp,
    car_transmission    varchar(50),
    car_engine_type     varchar(50),
    car_body_type       varchar(50),
    car_engine_capacity float8,
    car_engine_power    float8,
    car_odometer_type   varchar(50),
    car_color           varchar(50),
    current_mileage     float8,
    vrp                 varchar(10),
    user_id             bigint references users (id),
    primary key (id)
);

create table events
(
    id               bigint not null,
    car_id           bigint not null,
    date_event       timestamp,
    operation_title  varchar(255),
    consumables      varchar(255),
    qty              float8,
    price            float8,
    odometer_reading float8,
    note             text,
    primary key (id)
);

ALTER TABLE IF EXISTS cars
    add constraint car_user_fk foreign key (user_id) references users;

ALTER TABLE IF EXISTS events
    add constraint car_event_fk foreign key (car_id) references cars;

ALTER TABLE IF EXISTS user_role
    add constraint user_role_user_fk foreign key (user_id) references users;
