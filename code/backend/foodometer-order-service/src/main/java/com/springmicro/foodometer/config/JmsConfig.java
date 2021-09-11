package com.springmicro.foodometer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.util.MimeTypeUtils;

@Configuration
public class JmsConfig {

    @Bean
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
        DefaultContentTypeResolver contentTypeResolver = new DefaultContentTypeResolver();
        contentTypeResolver.setDefaultMimeType(MimeTypeUtils.TEXT_PLAIN);
        converter.setContentTypeResolver(contentTypeResolver);
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
