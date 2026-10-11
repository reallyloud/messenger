package com.example.messenger.db;

import com.example.messenger.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class JdbcMessageDao implements MessageDao {

    private final NamedParameterJdbcTemplate jdbc;

    //Экземпляр класса, реализующий функциональный интерфейс,
    //маппинг полей сообщения.
    private static final RowMapper<Message> MESSAGE_ROW_MAPPER =
            (ResultSet resultSet, int rowNum) -> new Message(
                    resultSet.getLong("message_id"),
                    getUuid(resultSet, "sender_id"),
                    getUuid(resultSet, "chat_id"),
                    resultSet.getString("message_text"),
                    getLocalDateTime(resultSet, "created_at")
            );

    @Override
    public Message insert(UUID chatId, UUID senderId, String text) {
        Objects.requireNonNull(chatId, "chatId must not be null");
        Objects.requireNonNull(senderId, "senderId must not be null");

        //message_id генерируется инкрементом в SQL (инкрементирует предыдущее
        // сообщение), не обязательно текущего пользователя.

        //1."Вставляем" саму структуру SQL запроса.
        String sql = """
                insert into messages
                (
                    chat_id,
                    sender_id,
                    message_text,
                    created_at
                )
                values
                (
                    :chatId,
                    :senderId,
                    :messageText,
                    :createdAt
                )
                """;

        LocalDateTime createdAt = LocalDateTime.now();

        //2.Подставляем конкретные значения вместо :chatId,:senderId...
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("chatId", chatId)
                .addValue("senderId", senderId)
                .addValue("messageText", text)
                .addValue("createdAt", createdAt);

        // Сюда мы в ответе получим message_id
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(
                sql,
                params,
                keyHolder,
                new String[]{"message_id"}
        );

        Number generatedId = keyHolder.getKey();

        if (generatedId == null) {
            throw new IllegalStateException("БД не вернула message_id во время insert message.");
        }

        return new Message(
                generatedId.longValue(),
                senderId,
                chatId,
                text,
                createdAt
        );
    }

//    @Override
//    public List<Message> findLatest(UUID chatId, int limit) {
//        Objects.requireNonNull(chatId, "chatId must not be null");
//
//        if (limit <= 0) {
//            return List.of();
//        }
//
//        String sql = """
//                select
//                    id,
//                    chat_id,
//                    sender_id,
//                    text,
//                    created_at
//                from
//                (
//                    select
//                        id,
//                        chat_id,
//                        sender_id,
//                        text,
//                        created_at
//                    from messages
//                    where chat_id = :chatId
//                    order by id desc
//                    limit :limit
//                ) as latest_messages
//                order by id asc
//                """;
//
//        MapSqlParameterSource params = new MapSqlParameterSource()
//                .addValue("chatId", chatId)
//                .addValue("limit", limit);
//
//        return jdbc.query(sql, params, MESSAGE_ROW_MAPPER);
//    }

//    @Override
//    public List<Message> findOlder(UUID chatId, Long beforeId, int limit) {
//        Objects.requireNonNull(chatId, "chatId must not be null");
//
//        if (beforeId == null || limit <= 0) {
//            return List.of();
//        }
//
//        String sql = """
//                select
//                    id,
//                    chat_id,
//                    sender_id,
//                    text,
//                    created_at
//                from
//                (
//                    select
//                        id,
//                        chat_id,
//                        sender_id,
//                        text,
//                        created_at
//                    from messages
//                    where chat_id = :chatId
//                      and id < :beforeId
//                    order by id desc
//                    limit :limit
//                ) as older_messages
//                order by id asc
//                """;
//
//        MapSqlParameterSource params = new MapSqlParameterSource()
//                .addValue("chatId", chatId)
//                .addValue("beforeId", beforeId)
//                .addValue("limit", limit);
//
//        return jdbc.query(sql, params, MESSAGE_ROW_MAPPER);
//    }

    private static UUID getUuid(ResultSet rs, String columnName) throws SQLException {
        Object value = rs.getObject(columnName);

        if (value == null) {
            return null;
        }

        if (value instanceof UUID uuid) {
            return uuid;
        }

        return UUID.fromString(value.toString());
    }

    private static LocalDateTime getLocalDateTime(ResultSet rs, String columnName) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnName);

        if (timestamp == null) {
            return null;
        }

        return timestamp.toLocalDateTime();
    }
}