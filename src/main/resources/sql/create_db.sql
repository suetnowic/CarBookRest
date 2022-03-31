DROP TABLE IF EXISTS user_role CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS cars CASCADE;

CREATE TABLE cars
(
    id        bigint not null,
    car_brand varchar(50),
    car_model varchar(50),
    user_id  bigint references users (id),
    primary key (id)
);

create table events
(
    id               bigint not null,
    car_id           bigint,
    date_event       varchar(4),
    operation_title  varchar(255),
    consumables      varchar(255),
    qty              float8,
    price            float8,
    odometer_reading float8,
    note             text,
    primary key (id)
);


CREATE TABLE user_role
(
    user_id int8 not null,
    roles   varchar(50)
);

CREATE TABLE users
(
    id       bigint       not null,
    username varchar(255) not null unique,
    email    varchar(255) not null unique,
    password varchar(120),
    primary key (id)
);

ALTER TABLE IF EXISTS cars
    add constraint car_user_fk foreign key (user_id) references users;

ALTER TABLE IF EXISTS events
    add constraint car_event_fk foreign key (car_id) references cars;

ALTER TABLE IF EXISTS user_role
    add constraint user_role_user_fk foreign key (user_id) references users;
