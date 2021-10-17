package com.springmicro.foodometer.web.mapper;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateMapper {
    public LocalDateTime asLocalDateTime(String date) {
        return LocalDateTime.parse(date);
    }

    public String asString(LocalDateTime localDate) {
        return localDate.toString();
    }
}
