package com.springmicro.foodometer.web.mapper;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateMapper {
    public LocalDate asLocalDate(String date) {
        return LocalDate.parse(date);
    }

    public String asString(LocalDate localDate) {
        return localDate.toString();
    }
}
