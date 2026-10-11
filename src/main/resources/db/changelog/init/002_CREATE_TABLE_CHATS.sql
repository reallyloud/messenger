--liquibase formatted sql
--changeset maxim:002
CREATE TABLE chats
(
    chat_id    UUID PRIMARY KEY, -- бигсериал это автоинкремент
    chat_title VARCHAR(200),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);
