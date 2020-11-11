ALTER table tweet add column reply_to_id  int default null;
ALTER table tweet alter column content type varchar(1000);
ALTER table tweet add column comments  int default 0;

CREATE table follow
(
    user_id     int not null,
    follow_user_id int not null
);


CREATE table likes
(
    user_id     int not null,
    like_post_id int not null
);

CREATE table shares
(
    user_id     int not null,
    share_post_id int not null
);

ALTER TABLE likes
    ADD PRIMARY KEY (user_id, like_post_id);
ALTER TABLE shares
    ADD PRIMARY KEY (user_id, share_post_id);
