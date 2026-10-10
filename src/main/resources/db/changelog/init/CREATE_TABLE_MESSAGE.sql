--liquibase formatted sql
--changeset maxim:001-create-messages
CREATE TABLE messages (
    message_id BIGSERIAL PRIMARY KEY, -- бигсериал это автоинкремент
    sender_id UUID NOT NULL,
    chat_id UUID NOT NULL,
    message_text TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);