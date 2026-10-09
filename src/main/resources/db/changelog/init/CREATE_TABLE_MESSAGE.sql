--liquibase formatted sql
--changeset maxim:001-create-messages
CREATE TABLE messages (
    message_id UUID PRIMARY KEY,
    message_text TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);