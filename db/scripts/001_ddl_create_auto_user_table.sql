create table auto_user
(
    id       INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    login    varchar not null unique,
    password varchar not null
);