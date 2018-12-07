create table employee_pc (
    id int8 NOT NULL,
    employee_id int8,
    pc_id int8,
    primary key (id)
);

create table tb_pc (
    id int8 NOT NULL,
    type_id int8,
    model_id int8,
    primary key (id)
);

create table model (
    id int8 NOT NULL,
    name varchar(255),
    primary key (id)
);

alter table if exists tb_pc
    add constraint tb_pc_model_fk
    foreign key (model_id) references model (id);

alter table if exists employee_pc
    add constraint employee_pc_employee_fk
    foreign key (employee_id) references employee (id);

alter table if exists employee_pc
    add constraint pc_id_pc_fk
    foreign key (pc_id) references tb_pc (id);