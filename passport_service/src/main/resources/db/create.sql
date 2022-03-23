CREATE TABLE IF NOT EXISTS passports
(
    id         SERIAL PRIMARY KEY NOT NULL,
    number     INT                NOT NULL,
    series     INT                NOT NULL,
    name       VARCHAR(20)        NOT NULL,
    surname    VARCHAR(20)        NOT NULL,
    patronymic VARCHAR(20)        NOT NULL DEFAULT '',
    validity   DATE               NOT NULL,
    CONSTRAINT NUMBER_SERIES UNIQUE (number, series)
);