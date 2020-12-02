create table original_tweet_content(
    id serial primary key ,
    content text,
    tweet_id int references tweet(id) unique
);
