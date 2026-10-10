package com.example.messenger.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

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
    private boolean own;
}
