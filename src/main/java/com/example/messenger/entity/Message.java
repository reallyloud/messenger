package com.example.messenger.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Message {
    private UUID messageId;
    private String messageText;
    private Date createdAt;
}
