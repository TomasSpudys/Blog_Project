insert into usr (username, password, active)
values ('admin', '123', true),
       ('user', 'user', true)  ;

insert into user_role (user_id, roles)
values (1, 'ADMIN'),
       (2,'USER');
