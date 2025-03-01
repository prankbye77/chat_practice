package kr.co.openprogramming.chat.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.openprogramming.chat.config.RabbitMQConfig;
import kr.co.openprogramming.chat.service.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static kr.co.openprogramming.chat.config.MapperConfig.OBJECT_MAPPER;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publish(ChatMessageDto chatMessageDto) {
        try {
            String messageJson = OBJECT_MAPPER.writeValueAsString(chatMessageDto);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "", messageJson);
            log.info("Message published: {}", messageJson);
        } catch (JsonProcessingException e) {
            log.error("Message serialization error", e);
        }
    }
}
