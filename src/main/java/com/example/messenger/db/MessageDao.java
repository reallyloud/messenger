package com.example.messenger.db;


import com.example.messenger.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageDao {

    Message insert(UUID chatId, UUID senderId, String text);

//    List<Message> findLatest(UUID chatId, int limit);
//
//    List<Message> findOlder(UUID chatId, Long lastMessageBeforeId, int limit);
}