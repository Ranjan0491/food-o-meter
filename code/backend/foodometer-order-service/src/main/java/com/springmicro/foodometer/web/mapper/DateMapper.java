package com.springmicro.foodometer.web.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class DateMapper {
    Timestamp toTimeStmap(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
