package kr.co.openprogramming.chat.service.dto;

public record ChatMessageDto(
        String sender,
        String roomId,
        String message,
        ChatMessageType type
) {
}
