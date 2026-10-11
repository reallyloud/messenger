package com.example.messenger.controller;

import com.example.messenger.dto.SendMessageDto;
import com.example.messenger.entity.Message;
import com.example.messenger.service.MessageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
//
//    private static final int PAGE_SIZE = 30;
    private final MessageService messageService;
//
//    @GetMapping("/chat/{chatId}")
//    public String chatPage(Model model, @PathVariable UUID chatId) {
//        List<Message> latest = messageService.getLatest(chatId, PAGE_SIZE + 1);
//
//        boolean hasMore = latest.size() > PAGE_SIZE;
//
//        if (hasMore) {
//            latest = latest.subList(0, PAGE_SIZE);
//        }
//
//        model.addAttribute("messages", latest);
//        model.addAttribute("hasMore", hasMore);
//        model.addAttribute("chatId", chatId);
//
//        return "chat";
//    }
//
//    @GetMapping(
//            value = "/chat/{chatId}/messages/older",
//            produces = MediaType.TEXT_HTML_VALUE
//    )
//    public String olderMessages(
//            @PathVariable UUID chatId,
//            @RequestParam(required = false) Long beforeId,
//            @RequestParam(defaultValue = "20") int limit,
//            Model model,
//            HttpServletResponse response
//    ) {
//        int safeLimit = Math.min(Math.max(limit, 1), 100);
//
//        List<Message> older = messageService.getOlder(chatId, beforeId, safeLimit + 1);
//
//        boolean hasMore = older.size() > safeLimit;
//
//        if (hasMore) {
//            older = older.subList(0, safeLimit);
//        }
//
//        model.addAttribute("messages", older);
//
//        response.setHeader("X-Has-More", String.valueOf(hasMore));
//
//        return "chat-fragments :: message-list";
//    }

    @PostMapping("/{chatId}")
    public String sendMessage(
            @PathVariable UUID chatId,
            @RequestBody SendMessageDto messageDto
    ) {
        Message savedMessage = messageService.saveMessage(
                chatId,
                messageDto.getMessageText()
        );
        return savedMessage.toString();

    }
}
