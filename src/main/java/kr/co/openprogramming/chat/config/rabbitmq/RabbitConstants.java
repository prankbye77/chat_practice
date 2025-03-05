package kr.co.openprogramming.chat.config.rabbitmq;

public class RabbitConstants {

    public static final String EXCHANGE_NAME = "chat.exchange";
    public static final String QUEUE_NAME = "chat.queue";

    public static final String PREFIX_ROUTING_KEY = "chat.room.";
    public static final String ROUTING_KEY = PREFIX_ROUTING_KEY + "#";
}
