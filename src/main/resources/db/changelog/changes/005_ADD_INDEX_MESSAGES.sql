--liquibase formatted sql
--changeset maxim:004
CREATE INDEX idx_messages_chat_created ON messages (chat_id, created_at DESC);