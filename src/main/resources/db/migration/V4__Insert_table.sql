insert into post (id, name)
        values (1, 'technician'), (2, 'laboratory_assistant'), (3, 'teacher'),
                    (4, 'professor'), (5, 'graduate_student'), (6, 'engineer');

insert into campus (id, name)
        values (1, 'building_1'), (2, 'building_2'), (3, 'building_3'),
                    (4, 'building_4'), (5, 'building_5');

insert into sub_division (id, name, phone, campus_id)
        values (1, 'chair_1', '11-11', 1), (2, 'chair_2', '22-22', 2),
                (3, 'chair_3', '33-33', 3), (4, 'chair_4', '44-44', 4),
                 (5, 'department_1', '55-55', 5), (6, 'department_2', '77-77', 1),
                  (7, 'department_3', '88-88', 2), (8, 'service_1', '99-99', 3),
                   (9, 'service_2', '12-12', 4), (10, 'DIT', '14-14', 5);

insert into employee (id, first_name, last_name, post_id, sub_division_id, user_ac)
        values (1, 'Dmitriy', 'Gontarenko', 1, 10, 1);

insert into tb_tag (id, name)
        values (1, 'fix'), (2, 'clear'), (3, 'tune'), (4, 'diagnostic'), (5, 'install');

