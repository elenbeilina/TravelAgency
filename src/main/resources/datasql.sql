create sequence hibernate_sequence;

create table users
(
  user_id    numeric not null
    constraint users_pk
    primary key,
  active     boolean not null,
  birthday   date    not null,
  email      varchar(255),
  first_name varchar(255),
  last_name  varchar(255),
  password   varchar(255),
  username   varchar(255)
    constraint uk_r43af9ap4edm43mmtq01oddj6
    unique
);

create table tours
(
  tour_id     numeric not null
    constraint tours_pk
    primary key,
  count_limit integer,
  description varchar(255),
  location    varchar(255),
  name        varchar(255),
  start_date  date    not null,
  end_date    date    not null
);

create table orders
(
  order_id  numeric not null
    constraint orders_pk
    primary key,
  confirmed boolean not null,
  tour_id   numeric
    constraint fkpeo9qed87g58smji2403gly7f
    references tours,
  user_id   numeric(19, 2)
    constraint fk32ql8ubntj5uh44ph9659tiih
    references users,
  time_key  timestamp
);

create table role
(
  role_id numeric not null
    constraint role_pkey
    primary key,
  role    varchar(255) default NULL :: character varying
);

create table user_role
(
  user_id numeric not null
    constraint fkj345gk1bovqvfame88rcx7yyx
    references users,
  role_id numeric not null
    constraint fka68196081fvovjhkek5m97n3y
    references role,
  constraint user_role_pkey
  primary key (user_id, role_id)
);


