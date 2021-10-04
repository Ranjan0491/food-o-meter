package com.springmicro.foodometer.web.mapper;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class DateMapper {
    public LocalDate asLocalDate(Date date) {
        return date.toLocalDate();
    }

    public Date asSqlDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }
}
