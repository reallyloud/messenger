package com.example.messenger.service;

import com.example.messenger.db.JdbcMessageDao;
import com.example.messenger.db.MessageDao;
import com.example.messenger.entity.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Getter
@Setter
@Service
public class MessageService {

     private final JdbcMessageDao messageDao;

//    private final List<Message> messages = new CopyOnWriteArrayList<>();
//    private final AtomicLong idGenerator = new AtomicLong();
//
//    public Message add(UUID senderId,UUID chatId, String text, boolean own) {
//        Message message = new Message(
//                idGenerator.incrementAndGet(),
//                senderId,
//                chatId,
//                text,
//                LocalDateTime.now(),
//                own
//        );
//
//        messages.add(message);
//
//        return message;
//    }
    public Message saveMessage(UUID chatId,String messageText) {
        Message message = messageDao.insert(
                chatId,
                UUID.randomUUID(),
                messageText
        );
        return message;
    }
//
//    public List<Message> getLatest(UUID chatId,int limit) {
//        int size = messages.size();
//        int from = Math.max(0, size - limit);
//
//        return new ArrayList<>(messages.subList(from, size));
//    }

//    public List<Message> getOlder(Long beforeId, int limit) {
//        if (beforeId == null || limit <= 0) {
//            return new ArrayList<>();
//        }
//
//        List<Message> result = new ArrayList<>();
//
//        for (int i = messages.size() - 1; i >= 0 && result.size() < limit; i--) {
//            Message message = messages.get(i);
//
//            if (message.getId() < beforeId) {
//                result.add(message);
//            }
//        }
//
//        Collections.reverse(result);
//
//        return result;
//    }
}