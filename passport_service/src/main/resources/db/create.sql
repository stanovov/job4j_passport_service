CREATE TABLE IF NOT EXISTS emails
(
    id    SERIAL PRIMARY KEY NOT NULL,
    email VARCHAR(100)       NOT NULL
);

CREATE TABLE IF NOT EXISTS passports
(
    id         SERIAL PRIMARY KEY NOT NULL,
    number     INT                NOT NULL,
    series     INT                NOT NULL,
    name       VARCHAR(20)        NOT NULL,
    surname    VARCHAR(20)        NOT NULL,
    patronymic VARCHAR(20)        NOT NULL DEFAULT '',
    validity   DATE               NOT NULL,
    email_id   INT,
    FOREIGN KEY (email_id) REFERENCES emails,
    CONSTRAINT NUMBER_SERIES UNIQUE (number, series)
);

CREATE TABLE IF NOT EXISTS email_notifications
(
    id          SERIAL PRIMARY KEY NOT NULL,
    email_id    INT,
    last_notify TIMESTAMP          NOT NULL
);