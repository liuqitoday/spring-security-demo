create table user
(
    user_id  int auto_increment
        primary key,
    username varchar(255) not null,
    password varchar(255) null,
    role     varchar(255) null,
    constraint user_username_uindex
        unique (username)
);

INSERT INTO liuqitech.user (user_id, username, password, role) VALUES (1, 'liuqitech', '$2a$10$e/9/J1RJUoF9xg1HfCtRKeltP6N6COjfOS04plx9QKMkMh7L6rsn.', 'admin');