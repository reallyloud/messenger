package com.example.messenger.entity;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Chat {
    private UUID chatId;
    private LocalDateTime createdAt;
}
