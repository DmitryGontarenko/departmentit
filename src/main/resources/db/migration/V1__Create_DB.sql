create sequence hibernate_sequence start 1 increment 1;

create table campus (id int8 not null, name varchar(255), primary key (id));
create table employee (id int8 not null, first_name varchar(255), last_name varchar(255), post_id int8, sub_division_id int8, user_ac int8, primary key (id));
create table employee_pc (id int8 not null, employee_id int8, pc_id int8, primary key (id));
create table orders (id int8 not null, created_at timestamp, status integer, tag_id int8, text varchar(2048), updated_at timestamp, user_id int8, primary key (id));
create table post (id int8 not null, name varchar(255), primary key (id));
create table sub_division (id int8 not null, name varchar(255), phone varchar(255), campus_id int8, primary key (id));
create table tb_pc (id int8 not null, model_id int8, type_id int8, primary key (id));
create table tb_user (id int8 not null, activation_code varchar(255), active boolean not null, created_at timestamp, email varchar(255), password varchar(255), username varchar(255), primary key (id));
create table user_role (user_id int8 not null, roles varchar(255));
create table tb_tag (id int8 not null, name varchar(255), primary key(id));

alter table if exists employee add constraint employee_post foreign key (post_id) references post;
alter table if exists employee add constraint employee_sub_division foreign key (sub_division_id) references sub_division;
alter table if exists employee add constraint employee_tb_user foreign key (user_ac) references tb_user;
alter table if exists employee_pc add constraint employee_pc_employee foreign key (employee_id) references employee;
alter table if exists employee_pc add constraint employee_pc_tb_pc foreign key (pc_id) references tb_pc;
alter table if exists orders add constraint orders_tb_pc foreign key (user_id) references tb_user;
alter table if exists orders add constraint orders_tag_fk foreign key (tag_id) references tb_tag;
alter table if exists sub_division add constraint sub_division_campus foreign key (campus_id) references campus;
alter table if exists user_role add constraint user_role_tb_user foreign key (user_id) references tb_user;
