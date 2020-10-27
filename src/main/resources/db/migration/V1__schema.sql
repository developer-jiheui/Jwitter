CREATE table users
(
    id          serial primary key,
    username    varchar(255) not null unique,
    email       varchar(255) not null unique,
    birthday    timestamptz  not null,
    password    varchar(255) not null,
    created_at  timestamptz  not null default now(),
    modified_at timestamptz  not null default now(),
    avatar      varchar(255)          default null,
 );

create table tweet
(
    id          serial primary key,
    content     varchar(255),
    user_id     int references users (id) not null,
    created_at  timestamptz               not null default now(),
    modified_at timestamptz               not null default now(),
    likes       int                       not null default 0,
    shares      int                       not null default 0,
    photo       varchar(255)                       default null
);

