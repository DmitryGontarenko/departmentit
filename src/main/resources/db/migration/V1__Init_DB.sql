create sequence hibernate_sequence start 1 increment 1;

create table orders (
    id int8 not null,
    tag varchar(255),
    text varchar(2048),
    user_id int8,
    created_at timestamp,
    updated_at timestamp,
    primary key (id)
);

create table tb_user (
    id int8 not null,
    activation_code varchar(255),
    active boolean not null,
    email varchar(255),
    password varchar(255),
    username varchar(255),
    created_at timestamp,
    primary key (id)
);

create table employee (
    id int8 not null,
    first_name varchar(255),
    last_name varchar(255),
    post_id int8,
    sub_division_id int8,
    user_ac int8,
    primary key (id)
);

alter table if exists employee
    add constraint employee_tb_user_fk
    foreign key (user_ac) references tb_user (id);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

alter table if exists orders
    add constraint orders_user_fk
    foreign key (user_id) references tb_user;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references tb_user;
