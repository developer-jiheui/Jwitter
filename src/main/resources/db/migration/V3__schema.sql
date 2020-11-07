create table bookmarks(
    id serial not null primary key ,
    user_id int references users(id),
    twit_id int references tweet(id)
);
