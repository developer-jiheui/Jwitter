create table reports
(
    id          serial not null primary key,
    tweet_id    int references tweet(id) ON DELETE CASCADE,
    user_id     int references users(id) ON DELETE CASCADE,
    report_date timestamp without time zone default (now() at time zone 'Canada/Pacific'),
    report_content     varchar(255),
    CONSTRAINT UC_Report UNIQUE(tweet_id,user_id)
);
