package kr.co.openprogramming.chat.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static kr.co.openprogramming.chat.config.rabbitmq.RabbitConstants.*;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue chatQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public Binding binding(Queue chatQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(chatQueue).to(topicExchange).with(ROUTING_KEY);
    }
}
