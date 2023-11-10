--liquibase formatted sql

--changeset sbukaevsky:1
CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY UNIQUE,
    name     VARCHAR(64) NOT NULL,
    age      INTEGER     NOT NULL,
    email    VARCHAR(64) NOT NULL UNIQUE,
    password VARCHAR     NOT NULL,
    status   VARCHAR(16) NOT NULL
);

CREATE TABLE homes
(
    id      BIGSERIAL PRIMARY KEY UNIQUE,
    address VARCHAR(128) NOT NULL
);

--changeset sbukaevsky:4
ALTER TABLE homes
    ADD COLUMN user_id BIGSERIAL;