insert into tb_user (id, username, password, active, created_at)
        values (1, 'admin', '123', true, CURRENT_TIMESTAMP);

insert into user_role (user_id, roles)
        values (1, 'USER'), (1, 'ADMIN');