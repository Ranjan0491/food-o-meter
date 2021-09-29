package com.springmicro.foodometer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class FoodItemExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception exception) {
        log.error("Error occurred", exception);
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .localDateTime(LocalDateTime.now())
                .message(exception.getMessage())
                .stackTrace(getStackTraceList(exception))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    private List<String> getStackTraceList(Exception exception) {
        List<String> stackTraceList = Arrays.stream(exception.getStackTrace()).map(stackTraceElement -> "\tat "+stackTraceElement).collect(Collectors.toList());
        stackTraceList.add(0, exception.toString());
        return stackTraceList;
    }
}
