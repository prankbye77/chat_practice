package kr.co.openprogramming.chat.controller;

import kr.co.openprogramming.chat.service.dto.ChatMessageDto;
import kr.co.openprogramming.chat.service.message.ChatPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    private final ChatPublisher chatPublisher;

    @MessageMapping("/send")
    public void sendMessage(ChatMessageDto chatMessageDto) {
        chatPublisher.publish(chatMessageDto);
    }
}
