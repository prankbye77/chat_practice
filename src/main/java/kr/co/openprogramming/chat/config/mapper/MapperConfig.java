package kr.co.openprogramming.chat.config.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperConfig {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
}
