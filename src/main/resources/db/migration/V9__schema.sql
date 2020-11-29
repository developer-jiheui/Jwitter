alter table users add column enabled boolean not null default true;
insert into users(username,email,password,birthday) values ('admin','admin@gmail.com','$2a$10$mhtJllQv8azarsyYDMXKgeSX73MrcCFbssm/VQQPNOzrKx9ttqWF.',now());

