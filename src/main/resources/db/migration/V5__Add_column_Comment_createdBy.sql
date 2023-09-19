alter table comment add column created_by_id bigint default 1;

alter table if exists comment
    add constraint comment_user_fk
    foreign key (created_by_id) references usr;

