package com.example.messenger.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Message {
    private Long messageId;
    private final UUID senderId;
    private final UUID chatId;
    private String messageText;
    private LocalDateTime createdAt;
}
