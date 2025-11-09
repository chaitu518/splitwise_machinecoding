package com.srt.splitwise.Exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GroupRelatedException.class)
    public String handleGroupRelatedException(GroupRelatedException e) {
        return e.getMessage();
    }
}
