create sequence hibernate_sequence start 1 increment 1;

create table orders (
    id int8 not null,
    tag varchar(255),
    text varchar(2048),
    user_id int8,
    primary key (id)
);

create table tb_user (
    id int8 not null,
    activation_code varchar(255),
    active boolean not null,
    email varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);

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