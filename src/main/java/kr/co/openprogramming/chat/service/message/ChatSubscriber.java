package kr.co.openprogramming.chat.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.openprogramming.chat.service.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static kr.co.openprogramming.chat.config.mapper.MapperConfig.OBJECT_MAPPER;
import static kr.co.openprogramming.chat.config.rabbitmq.RabbitConstants.QUEUE_NAME;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatSubscriber {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = QUEUE_NAME)
    public void subscribe(String message) {
        try {
            ChatMessageDto chatMessage = OBJECT_MAPPER.readValue(message, ChatMessageDto.class);
            String destination = "/topic/" + chatMessage.getRoomId();
            simpMessagingTemplate.convertAndSend(destination, chatMessage);
            log.info("Message delivered to [{}]: {}", destination, chatMessage);
        } catch (JsonProcessingException e) {
            log.error("Message deserialization error", e);
        }
    }
}
