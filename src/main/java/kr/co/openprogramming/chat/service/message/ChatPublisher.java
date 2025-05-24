package kr.co.openprogramming.chat.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.openprogramming.chat.service.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static kr.co.openprogramming.chat.config.mapper.MapperConfig.OBJECT_MAPPER;
import static kr.co.openprogramming.chat.config.rabbitmq.RabbitConstants.EXCHANGE_NAME;
import static kr.co.openprogramming.chat.config.rabbitmq.RabbitConstants.PREFIX_ROUTING_KEY;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(ChatMessageDto chatMessageDto) {
        try {
            String routingKey = PREFIX_ROUTING_KEY + chatMessageDto.roomId();
            String messageJson = OBJECT_MAPPER.writeValueAsString(chatMessageDto);
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, routingKey, messageJson);
            log.info("Message published to [{}]: {}", routingKey, messageJson);
        } catch (JsonProcessingException e) {
            log.error("Message serialization error", e);
        }
    }
}
