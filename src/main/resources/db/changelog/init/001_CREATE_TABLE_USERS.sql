--liquibase formatted sql
--changeset maxim:001
CREATE TABLE users
(
    user_id         UUID PRIMARY KEY,
    user_name   TEXT      NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);