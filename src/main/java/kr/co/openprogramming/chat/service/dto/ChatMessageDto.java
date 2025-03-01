package kr.co.openprogramming.chat.service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatMessageDto {

    private String sender;

    private String roomId;

    private String message;
}
