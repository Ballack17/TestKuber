

CREATE TABLE yandex_master.essence
(
    id          uuid NOT NULL
        constraint essence_pkey
            primary key,
    some_date   DATE NOT NULL DEFAULT CURRENT_DATE,
    some_time   TIME NOT NULL default CURRENT_TIME,
    title       VARCHAR(255) NOT NULL,
    email       VARCHAR(255),
    constraint "email"
        unique (email)
);

