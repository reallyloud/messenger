--liquibase formatted sql
--changeset maxim:005-create-messages
CREATE TABLE messages
(
    message_id   BIGSERIAL PRIMARY KEY, -- бигсериал это автоинкремент
    sender_id    UUID                     NOT NULL,
    chat_id      UUID                     NOT NULL,
    message_text TEXT                     NOT NULL,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL,
    FOREIGN KEY (chat_id) REFERENCES chats (chat_id) ON DELETE CASCADE,
    FOREIGN KEY (sender_id) REFERENCES users (user_id) ON DELETE CASCADE
);

create index idx_messages_chat_id
    on messages (chat_id, message_id desc);