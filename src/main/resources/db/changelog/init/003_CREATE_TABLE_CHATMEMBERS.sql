--liquibase formatted sql
--changeset maxim:004
CREATE TABLE chat_members
(
    chat_id   UUID      NOT NULL,
    user_id   UUID      NOT NULL,
    joined_at TIMESTAMP NOT NULL DEFAULT now(),

    PRIMARY KEY (chat_id, user_id),
    FOREIGN KEY (chat_id) REFERENCES chats (chat_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);
CREATE INDEX idx_chat_members_user ON chat_members (user_id);