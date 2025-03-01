package kr.co.openprogramming.chat.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.openprogramming.chat.config.RabbitMQConfig;
import kr.co.openprogramming.chat.service.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static kr.co.openprogramming.chat.config.MapperConfig.OBJECT_MAPPER;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatSubscriber {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void subscribe(String message) {
        try {
            ChatMessageDto chatMessageDto = OBJECT_MAPPER.readValue(message, ChatMessageDto.class);
            simpMessagingTemplate.convertAndSend("/topic/" + chatMessageDto.getRoomId(), chatMessageDto);
            log.info("Message subscribed: {}", chatMessageDto);
        } catch (JsonProcessingException e) {
            log.error("Message deserialization error", e);
        }
    }
}
