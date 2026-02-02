create table history_owner
(
    id       INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    owner_id int not null references owner (id),
    car_id   int not null references car (id)
);