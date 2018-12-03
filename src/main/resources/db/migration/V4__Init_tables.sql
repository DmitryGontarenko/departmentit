create table campus (
    id int8 not null,
    name varchar(255) not null,
    primary key (id)
);

create table sub_division (
    id int8 not null,
    name varchar(255),
    phone varchar(255),
    campus_id int8,
    primary key (id)
);

create table employee (
    id int8 not null,
    first_name varchar(255),
    last_name varchar(255),
    created_at timestamp,
    post_id int8,
    sub_division_id int8,
    primary key (id)
);

create table post (
    id int8 not null,
    name varchar(255),
    primary key(id)
);

alter table if exists sub_division
    add constraint sub_division_campus_fk
    foreign key (campus_id) references campus;

alter table if exists employee
    add constraint employee_post_fk
    foreign key (post_id) references post;

alter table if exists employee
    add constraint employee_sub_division_fk
    foreign key (sub_division_id) references sub_division;