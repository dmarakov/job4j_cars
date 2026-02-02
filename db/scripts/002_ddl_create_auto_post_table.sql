create table auto_post
(
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    description  varchar not null,
    created      timestamp,
    auto_user_id int references auto_user (id)
);